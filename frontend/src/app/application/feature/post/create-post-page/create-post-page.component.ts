import {Component, ElementRef, OnInit} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {PostCreationRequest} from "./post-creation-request";
import {from, Observable} from 'rxjs';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserPostsControllerService} from "../user-posts-controller/user-posts-controller.service";


@Component({
  selector: 'app-create-post-page',
  templateUrl: './create-post-page.component.html',
  styleUrls: ['./create-post-page.component.scss']
})
export class CreatePostPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  windowId: number;
  style: CSSStyleDeclaration;

  form: FormGroup

  content = new FormControl('', Validators.required);
  visibilityGroup = new FormControl('FRIENDS', Validators.required);

  constructor(
    private fb: FormBuilder,
    private windowManagementService: WindowManagementService,
    private userPostController: UserPostsControllerService,
    private elementRef: ElementRef
  ) {

    this.title = "Create post";
    this.icon = "assets/icons/create-post-page-icon.png";
    this.windowId = this.windowManagementService.getId();
    this.style = elementRef.nativeElement.style;

    this.form = fb.group({
      content: this.content,
      visibilityGroup: this.visibilityGroup
    });
    new Observable(
      (subscriber) => {
        setTimeout(()=>{
          subscriber.complete();
        }, 1);
      }
    ).subscribe();
  }
  ngOnInit(): void {
  }

  ngAfterViewInit(){
    new Observable(
      (subscriber) => {
        setTimeout(()=>{
          subscriber.complete();
        }, 1);
      }
    ).subscribe();
  }

  publishPost() {
    if(this.visibilityGroup.value !== "CUSTOM") {
      from(this.userPostController.addPost(
        new PostCreationRequest(
          this.content.value,
          this.visibilityGroup.value
        )
      )).subscribe(
        response => this.close(),
        error => window.alert(error.error())
      );
    } else {
      from(this.userPostController.addPost(
        new PostCreationRequest(
          this.content.value,
          this.visibilityGroup.value,
          [1,2,3]
        )
      )).subscribe(
        response => this.close(),
        error => window.alert(error.error())
      );
    }
  }
  close(): void {
    this.windowManagementService.closeWindow(this.windowId);
  }
  focus(): void {
    this.windowManagementService.focusWindow(this.windowId);
  }
  getIcon(): string | null {
    return this.icon;
  }
  getTitle(): string | Observable<string> {
    return this.title;
  }

  maximize(): void {
    this.windowManagementService.maximizeWindow(this.windowId);
  }
  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);

  }

  setId(windowId: number): void {
  }

}
