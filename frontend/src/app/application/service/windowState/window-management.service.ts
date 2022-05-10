import {ComponentFactoryResolver, ComponentRef, Inject, Injectable, ViewContainerRef} from '@angular/core';
import {WindowDto} from "./window-dto";
import {WindowType} from "./window-type";
import {UserProfilePageComponent} from "../../feature/profile/user-profile-page/user-profile-page.component";
import {TaskbarItemComponent} from "../../feature/core/taskbar-menu/taskbar-item/taskbar-item.component";
import {ChangePasswordPageComponent} from "../../feature/settings/change-password-page/change-password-page.component";
import {DeleteAccountPageComponent} from "../../feature/settings/delete-account-page/delete-account-page.component";
import {CreatePostPageComponent} from "../../feature/post/create-post-page/create-post-page.component";

@Injectable({
  providedIn: 'root'
})
export class WindowManagementService {

  currentZIndex: number;
  windowList:  Map<Number, HTMLElement>;
  taskBarList: Map<Number, HTMLElement>;
  idCounter: number;
  currentFocusedTaskbarElement: HTMLElement | undefined;
  currentFocusedWindowElement: HTMLElement | undefined;
  windowContainerRef : ViewContainerRef | undefined;
  taskbarContainerRef : ViewContainerRef | undefined;

  constructor() {
    this.currentZIndex = 10;
    this.idCounter = 0;
    this.windowList = new Map<Number, HTMLElement>();
    this.taskBarList= new Map<Number, HTMLElement>();
    this.currentFocusedTaskbarElement = undefined;
    this.currentFocusedWindowElement = undefined;
  }

  setWindowContainerRef(windowContainerRef: ViewContainerRef) {
    this.windowContainerRef = windowContainerRef;
  }

  setTaskbarContainerRef(taskbarContainerRef: ViewContainerRef){
    this.taskbarContainerRef = taskbarContainerRef;
  }

  openWindow(window: WindowDto) {
    let newDesktopPage! : ComponentRef<any>;
    switch (window.windowType) {
      case WindowType.ProfilePage:{
        if(this.windowContainerRef != undefined){
          newDesktopPage = this.windowContainerRef.createComponent(UserProfilePageComponent);
          newDesktopPage.location.nativeElement.setAttribute("userId", window.content?.userId);
          this.windowList.set(this.idCounter, newDesktopPage.location.nativeElement);
        }
        break;
      }

      case WindowType.MessageBox:
        break;

      case WindowType.ChangePasswordPage:{
        if(this.windowContainerRef != undefined){
          newDesktopPage = this.windowContainerRef.createComponent(ChangePasswordPageComponent);
          this.windowList.set(this.idCounter, newDesktopPage.location.nativeElement);
        }
        break;
      }

      case WindowType.DeleteAccountPage:{
        if(this.windowContainerRef != undefined){
          newDesktopPage = this.windowContainerRef.createComponent(DeleteAccountPageComponent);
          this.windowList.set(this.idCounter, newDesktopPage.location.nativeElement);
        }
        break;
      }

      case WindowType.CreatePostPage:{
        if(this.windowContainerRef != undefined){
          newDesktopPage = this.windowContainerRef.createComponent(CreatePostPageComponent);
          this.windowList.set(this.idCounter, newDesktopPage.location.nativeElement);
        }
        break;
      }
    }
    if (this.taskbarContainerRef){
      let newTaskBarItem = this.taskbarContainerRef.createComponent(TaskbarItemComponent);
      newTaskBarItem.instance.setIcon(newDesktopPage.instance.getIcon());
      newTaskBarItem.instance.setTitle(newDesktopPage.instance.getTitle());
      this.taskBarList.set(this.idCounter, newTaskBarItem.location.nativeElement);
      this.focusWindow(this.idCounter);
    }
    this.idCounter++;
  }

  closeWindow(windowId: number) {
    this.windowList.get(windowId)?.remove();
    this.taskBarList.get(windowId)?.remove();
    this.windowList.delete(windowId);
    this.taskBarList.delete(windowId);
  }

  minimizeWindow(windowId: number) {
    this.windowList.get(windowId)?.setAttribute("hidden", "");
    this.taskBarList.get(windowId)?.setAttribute("hiddenState", "true");
    this.currentFocusedTaskbarElement?.setAttribute("focused","false");
  }

  unminimizeWindow(windowId: number) {
    this.windowList.get(windowId)?.removeAttribute("hidden");
    this.taskBarList.get(windowId)?.setAttribute("hiddenState", "false");
    this.currentFocusedTaskbarElement = this.taskBarList.get(windowId);
    this.currentFocusedTaskbarElement?.setAttribute("focused","true");
  }

  maximizeWindow(windowId: number) {
  }

  focusWindow(windowId: number) {
    this.currentFocusedTaskbarElement?.setAttribute("focused", "false");
    this.currentFocusedTaskbarElement = this.taskBarList.get(windowId);
    this.currentFocusedWindowElement = this.windowList.get(windowId);
    this.currentFocusedTaskbarElement?.setAttribute("focused","true");
    if(this.currentFocusedWindowElement != undefined){
      this.currentFocusedWindowElement.style.zIndex = `${this.currentZIndex++}`;
    }
  }
  unfocusWindow(windowId: number) {
    this.currentFocusedTaskbarElement?.setAttribute("focused","false");

  }

  getId() {
    return this.idCounter;
  }
}
