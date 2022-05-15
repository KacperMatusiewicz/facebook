import {Component, ElementRef, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {UserPostsControllerService} from "../user-posts-controller/user-posts-controller.service";
import {from, Observable} from "rxjs";
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {Post} from "../../../service/post";
import {UpdatePostRequest} from "../update-post-request";

@Component({
  selector: 'app-edit-post-page',
  templateUrl: './edit-post-page.component.html',
  styleUrls: ['./edit-post-page.component.scss']
})
export class EditPostPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  windowId: number;
  style: CSSStyleDeclaration;
  form: FormGroup
  editedPost: Post | undefined;
  content = new FormControl('', Validators.required);
  visibilityGroup = new FormControl('FRIENDS', Validators.required);
  @Input()
  postId!: number;

  constructor(
    private fb: FormBuilder,
    private windowManagementService: WindowManagementService,
    private userPostController: UserPostsControllerService,
    private elementRef: ElementRef
  ) {

    this.title = "Edit post";
    this.icon = "assets/icons/create-post-page-icon.png";
    this.windowId = this.windowManagementService.getId();
    this.style = elementRef.nativeElement.style;

    this.form = fb.group({
      content: this.content,
      visibilityGroup: this.visibilityGroup
    });
    this.visibilityGroup.disable();
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

  public setPostContent(postId: number) {
    this.editedPost = this.userPostController.getPostById(postId);
    this.content.setValue(this.editedPost?.content);
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

  editPost() {
    if(this.editedPost?.id !== undefined){
      let id: number = this.editedPost.id;
        from(this.userPostController.editPost(
          new UpdatePostRequest(
            id,
            this.content.value
          )
        )).subscribe(
          response => this.close(),
          error => console.log("error")
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
