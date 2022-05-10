import {AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TaskbarItem} from "../../../../service/windowState/taskbar-item";
import {WindowManagementService} from "../../../../service/windowState/window-management.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-taskbar-item',
  templateUrl: './taskbar-item.component.html',
  styleUrls: ['./taskbar-item.component.scss']
})
export class TaskbarItemComponent implements OnInit,DoCheck, AfterViewInit, TaskbarItem {

  icon: string | undefined;
  windowId: number;
  title: string = "";
  focused: boolean = false;
  hidden: boolean = false;

  @ViewChild("icon")
  iconPlaceholder!: ElementRef<HTMLElement>;
  @ViewChild("container")
  container!: ElementRef<HTMLElement>;
  @ViewChild("wrapper")
  wrapper!: ElementRef<HTMLElement>;
  private elementsReady: boolean =false;


  constructor(private windowManagementService: WindowManagementService, private elementRef: ElementRef) {
    this.windowId = windowManagementService.getId();
  }

  ngDoCheck(): void {

    this.checkWindowStateAttributes();

    if (this.focused && this.elementsReady) {
      this.container.nativeElement.classList.add("container-focused");
      this.wrapper.nativeElement.classList.add("wrapper-focused");
    }
    if (!this.focused && this.elementsReady) {
      this.container.nativeElement.classList.remove("container-focused");
      this.wrapper.nativeElement.classList.remove("wrapper-focused");
    }
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.iconPlaceholder.nativeElement.style.backgroundImage =`url("${this.icon}")`;
    this.elementsReady = true;
  }

  onLeftClick() {
    if(this.focused) {
      this.minimize();
    } else {
      this.focus();
    }
    if(this.hidden) {
      this.unMinimize();
    }
  }

  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);
  }

  unMinimize(): void {
    this.windowManagementService.unminimizeWindow(this.windowId)
  }

  focus() {
    this.windowManagementService.focusWindow(this.windowId);
  }

  setIcon(icon: string | null): void {
    if(icon != null) {
      this.icon = icon;
    }
  }

  setTitle(title: string | Observable<string>): void {
    if(typeof title === "string") {
      this.title = title;
    } else {
      title.subscribe(
        value => this.title = value,
        error => window.alert(error.error)
      );
    }
  }

  private checkWindowStateAttributes() {
    if (this.elementRef.nativeElement.getAttribute("hiddenState") === "true") {
      this.hidden = true;
    } else {
      this.hidden = false;
    }
    if (this.elementRef.nativeElement.getAttribute("focused") === "true") {
      this.focused = true;
    } else {
      this.focused = false;
    }
  }
}
