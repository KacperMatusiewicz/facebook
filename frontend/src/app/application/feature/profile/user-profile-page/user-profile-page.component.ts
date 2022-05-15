import {Component, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {UserDetails} from "../../../service/user-details";
import {Post} from "../../../service/post";
import {UserDetailsService} from "../../../service/user-details.service";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {UserPostsStoreService} from "../../post/user-posts-model/user-posts-store.service";
import {WindowType} from "../../../service/windowState/window-type";
import {UserPostsControllerService} from "../../post/user-posts-controller/user-posts-controller.service";

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit, DesktopWindow {

  @Input()
  userId: number | undefined;

  windowId: number;

  userDetailsObservable!: Observable<UserDetails>

  @Output()
  closeWindowEvent = new EventEmitter<any>();

  userDetails: UserDetails;
  posts : Post[];
  icon: string;
  style: CSSStyleDeclaration;

  constructor(
    private userDetailsService: UserDetailsService,
    private windowManagementService: WindowManagementService,
    private elementRef: ElementRef,
    private userPostsStore: UserPostsStoreService,
    private userPostsController: UserPostsControllerService
  ) {
    this.style = elementRef.nativeElement.style;
    this.icon = "assets/icons/user.png";
    this.userDetails = new UserDetails("", "", "");
    this.posts = [];
    this.setUserDetailsObservable();
    this.setUserDetail();
    this.setPosts();
    this.windowId = windowManagementService.getId();

  }

  ngOnInit(): void {
  }

  private setUserDetailsObservable() {
    if(this.userId === undefined) {
      this.userDetailsObservable = this.userDetailsService.getUserDetails();
    } else {
      this.userDetailsObservable = this.userDetailsService.getUserDetailsBy(this.userId);
    }
  }

  private setUserDetail() {
    this.userDetailsObservable.subscribe(
      (response) => this.userDetails = response,
      (error) => window.alert(error.error)
    );
  }

  emitCloseWindowEvent() {
    this.close();
    this.closeWindowEvent.emit();
  }

  private setPosts() {
    if(this.userId === undefined){
      this.userPostsStore.userPostsObservable.subscribe({
        next: value => {this.posts = value}
      });
    } else {
      this.userDetailsService.getPostsBy(this.userId).subscribe(
        (response) => this.posts = response,
        (error) => window.alert(error.error)
      );
    }
  }

  public getFormattedDate(date: string): string{
    let dateFormat: Date = new Date(date);
    let timeF = new Intl.DateTimeFormat('pl-PL', {hour: '2-digit', minute: '2-digit'});
    let dateF = new Intl.DateTimeFormat('pl-PL', {day: '2-digit', month: '2-digit', year: 'numeric'});
    return `${dateF.format(dateFormat)} ${timeF.format(dateFormat)}`;
  }

  editPost(id: number) {
    this.windowManagementService.openWindow({
      windowType: WindowType.EditPostPage,
      content: {
        postId: id
      }
    })
  }

  deletePost(id: number) {
    this.userPostsController.removePost(id);
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

  setId(windowId: number): void {
    this.windowId = windowId;
  }

  getIcon(): string | null {
    return this.icon;
  }

  close(): void {
    this.windowManagementService.closeWindow(this.windowId);
  }

  getTitle(): Observable<string> {
    return this.userDetailsObservable.pipe(
      map(r => `${r.name}'s profile`)
    );
  }
}
