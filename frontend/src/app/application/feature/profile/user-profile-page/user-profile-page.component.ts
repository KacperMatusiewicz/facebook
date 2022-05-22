import {Component, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {UserDetails} from "../../../service/user-details";
import {Post} from "../../../service/post";
import {UserDetailsService} from "../../../service/user-details.service";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {UserPostsStoreService} from "../../../service/post/user-posts-model/user-posts-store.service";
import {UserPostsControllerService} from "../../../service/post/user-posts-controller/user-posts-controller.service";
import {UserRelationsControllerService} from "../../../service/relation/controller/user-relations-controller.service";
import {UserRelationsStoreService} from "../../../service/relation/store/user-relations-store.service";

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit, DesktopWindow {

  @Input()
  userId: number | undefined;

  windowId: number;

  titleObservable!: Subject<string>

  @Output()
  closeWindowEvent = new EventEmitter<any>();

  userDetails: UserDetails;
  posts : Post[];
  icon: string;
  style: CSSStyleDeclaration;

  canFollow: boolean;
  canUnfollow: boolean;
  canSendFriendRequest: boolean;
  canRemoveFriend: boolean;

  constructor(
    private userDetailsService: UserDetailsService,
    private windowManagementService: WindowManagementService,
    private elementRef: ElementRef,
    private userRelationsControllerService: UserRelationsControllerService,
    private userRelationsStore: UserRelationsStoreService
  ) {
    this.style = elementRef.nativeElement.style;
    this.icon = "assets/icons/user.png";
    this.userDetails = new UserDetails("", "", "");
    this.posts = [];
    this.windowId = windowManagementService.getId();
    this.titleObservable = new Subject<string>();

    this.canFollow = false;
    this.canUnfollow = false;
    this.canSendFriendRequest = false;
    this.canRemoveFriend = false;
  }

  ngOnInit(): void {
  }

  loadUserDetails(userId:number) {
    this.userId = userId;
    this.userDetailsService.getUserDetailsBy(userId).subscribe(
      (response) => {
        this.userDetails = response;
        this.titleObservable.next(`${response.name} ${response.lastName}'s profile`);
      },
      (error) => window.alert(error.error)
    );
    this.canFollow = this.userRelationsStore.checkIfCanFollow(userId);
    this.canUnfollow = this.userRelationsStore.checkIfCanUnfollow(userId);
    this.canSendFriendRequest = this.userRelationsStore.checkIfCanSendFriendRequest(userId);
    this.canRemoveFriend = this.userRelationsStore.checkIfCanRemoveFriend(userId);
  }


  emitCloseWindowEvent() {
    this.close();
    this.closeWindowEvent.emit();
  }

  setPosts(userId: number) {
      this.userDetailsService.getPostsBy(userId).subscribe(
        (response) => this.posts = response,
        (error) => window.alert(error.error)
      );
  }

  public getFormattedDate(date: string): string{
    let dateFormat: Date = new Date(date);
    let timeF = new Intl.DateTimeFormat('pl-PL', {hour: '2-digit', minute: '2-digit'});
    let dateF = new Intl.DateTimeFormat('pl-PL', {day: '2-digit', month: '2-digit', year: 'numeric'});
    return `${dateF.format(dateFormat)} ${timeF.format(dateFormat)}`;
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
    return this.titleObservable;
  }

  followUser() {
    if (this.userId === undefined)
      return;

    this.userRelationsControllerService.followUser(this.userId);
  }

  unfollowUser() {
    if (this.userId === undefined)
      return;

    this.userRelationsControllerService.unfollowUser(this.userId);
  }

  befriendUser() {
    if (this.userId === undefined)
      return;

    this.userRelationsControllerService.sendFriendRequest(this.userId);
  }

  removeFriend() {
    if (this.userId === undefined)
      return;

    this.userRelationsControllerService.unfriendUser(this.userId);
  }

  checkIfCanFollow(): boolean{
    if(this.userId !== undefined)
      return this.userRelationsStore.checkIfCanFollow(this.userId);

    return false;
  }

  checkIfCanUnfollow(): boolean{
    if(this.userId !== undefined)
      return this.userRelationsStore.checkIfCanUnfollow(this.userId);

    return false;
  }

  checkIfCanSendFriendRequest(): boolean{
    if (this.userId !== undefined)
      return this.userRelationsStore.checkIfCanSendFriendRequest(this.userId);

    return false;
  }

  checkIfCanRemoveFriend(): boolean{
    if (this.userId !== undefined)
      return this.userRelationsStore.checkIfCanRemoveFriend(this.userId);

    return false;
  }
}
