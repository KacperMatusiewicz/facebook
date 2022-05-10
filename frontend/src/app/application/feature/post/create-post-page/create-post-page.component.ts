import {Component, ElementRef, OnInit} from '@angular/core';
import { Observable } from 'rxjs';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {PostCreationRequest} from "./post-creation-request";
import {PostService} from "../post.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-create-post-page',
  templateUrl: './create-post-page.component.html',
  styleUrls: ['./create-post-page.component.scss']
})
export class CreatePostPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  private windowId: number;
  style: CSSStyleDeclaration;
  form: FormGroup
  content = new FormControl('', Validators.required);
  visibilityGroup = new FormControl('FRIENDS', Validators.required);

  constructor(
    private windowManagementService: WindowManagementService,
    private postService: PostService,
    private fb: FormBuilder,
    private elementRef: ElementRef
  ) {
    this.style = elementRef.nativeElement.style;
    this.form = fb.group({
      content: this.content,
      visibilityGroup: this.visibilityGroup
    })
    this.windowId = this.windowManagementService.getId();
    this.icon = "assets/icons/create-post-page-icon.png";
    this.title = "Create post";
  }

  getIcon(): string | null {
    return this.icon;
  }
  getTitle(): string | Observable<string> {
    return this.title;
  }
  close(): void {
    this.windowManagementService.closeWindow(this.windowId);
  }
  focus(): void {
    this.windowManagementService.focusWindow(this.windowId);
  }
  maximize(): void {
    this.windowManagementService.maximizeWindow(this.windowId);
  }
  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);

  }

  publishPost() {
    console.log(this.visibilityGroup.value);
    if(this.visibilityGroup.value !== "CUSTOM") {
      this.postService.createPost(
        new PostCreationRequest(
          this.content.value,
          this.visibilityGroup.value
        )
      ).subscribe(
        response => this.content.reset(),
        error => window.alert(error.error())
      );
    } else {
      this.postService.createPost(
        new PostCreationRequest(
          this.content.value,
          this.visibilityGroup.value,
          [1,2,3]
        )
      ).subscribe(
        response => this.content.reset(),
        error => window.alert(error.error())
      );
    }
  }

  setId(windowId: number): void {
      this.windowId = windowId;
  }

  ngOnInit(): void {
  }

}
