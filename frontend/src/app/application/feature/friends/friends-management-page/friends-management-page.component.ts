import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {UserRelationsStoreService} from "../../../service/relation/store/user-relations-store.service";
import {UserRelationsControllerService} from "../../../service/relation/controller/user-relations-controller.service";
import {Observable} from "rxjs";
import {UserDetailsService} from "../../../service/user-details.service";
import {UserListItem} from "../../../service/user-list-item";
import {WindowDto} from "../../../service/windowState/window-dto";
import {WindowType} from "../../../service/windowState/window-type";

@Component({
  selector: 'app-friends-management-page',
  templateUrl: './friends-management-page.component.html',
  styleUrls: ['./friends-management-page.component.scss']
})
export class FriendsManagementPageComponent implements OnInit, DesktopWindow {

  title: string;
  icon: string;
  style: CSSStyleDeclaration;
  windowId: number;
  friendsIds: number[] | undefined;
  private requestingUsersIds: number[] | undefined;
  receivedFriendRequests: number[] | undefined;

  friendsList: UserListItem[] = [];
  friendsListBase: UserListItem[] = [];
  requestingUsersListBase: UserListItem[] = [];
  requestingUsersList: UserListItem[] = [];

  @ViewChild('friends') friends!: ElementRef<HTMLElement>;
  @ViewChild('pending') pending!: ElementRef<HTMLElement>;
  selectedUserRequestId: number | undefined;
  selectedFriendId: number | undefined;


  constructor(
    private elemRef: ElementRef,
    private windowManagementService: WindowManagementService,
    private relationStore: UserRelationsStoreService,
    private relationController: UserRelationsControllerService,
    private userDetailsService: UserDetailsService,
  ) {
    this.title = "Manage Friends";
    this.icon = "assets/icons/friend.png";
    this.style = elemRef.nativeElement.style;
    this.windowId = windowManagementService.getId();
    relationStore.friendsSubject.subscribe(
      (next) => {
        this.friendsIds = next;
        this.friendsListBase = [];
        if(this.friendsIds.length === 0) {
          this.friendsList = [];
        }
        this.friendsIds.forEach(
          (friendId) => {
            this.userDetailsService.getUserDetailsBy(friendId).subscribe(
              (response) => {
                this.friendsListBase.push(
                  new UserListItem(
                    friendId,
                    response.name,
                    response.lastName
                  )
                );
                this.friendsList = this.friendsListBase;
                console.log("friends:")
                console.table(this.friendsList);
              }
            )
          }
        )
      }
    );
    relationStore.receivedFriendRequestsSubject.subscribe({
      next: (value)=> {
        this.requestingUsersIds = value;
        this.requestingUsersListBase = [];
        if(this.requestingUsersIds.length === 0) {
          this.requestingUsersList = [];
        }
        this.requestingUsersIds.forEach(
          (requestingUserId) => {

            this.userDetailsService.getUserDetailsBy(requestingUserId).subscribe(
              (response) => {
                this.requestingUsersListBase.push(
                  new UserListItem(
                    requestingUserId,
                    response.name,
                    response.lastName
                  )
                );
                this.requestingUsersList = this.requestingUsersListBase;
                console.log("Received friends requests:")
                console.table(this.requestingUsersList);
              }
            )
          }
        )
      }
    });
  }

  search($event: any) {
    let phrase = $event.target.value.toLowerCase();

    this.friendsList = [];
    this.friendsListBase.forEach(
      (f) => {
        let combined = `${f.name.toLowerCase()} ${f.lastName.toLowerCase()}`;
        if(combined.includes(phrase)){
          this.friendsList.push(f);
        }
      }
    )

    this.requestingUsersList = [];
    this.requestingUsersListBase.forEach(
      (f) => {
        let combined = `${f.name.toLowerCase()} ${f.lastName.toLowerCase()}`;
        if(combined.includes(phrase)){
          this.requestingUsersList.push(f);
        }
      }
    )
  }

  ngOnInit(): void {
  }

  acceptRequest() {
    if(this.selectedUserRequestId !== undefined) {
      this.relationController.acceptFriendRequest(this.selectedUserRequestId);
    }
  }

  denyFriendRequest(){
    if(this.selectedUserRequestId !== undefined) {
      this.relationController.denyFriendRequest(this.selectedUserRequestId);
    }
  }

  showFriendsTab(): void {
    this.friends.nativeElement.style.display="initial";
    this.pending.nativeElement.style.display="none";
  }

  showPendingTab(): void {
    this.friends.nativeElement.style.display="none";
    this.pending.nativeElement.style.display="initial";
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

  getTitle(): Observable<string> {
    return new Observable<string>(
      (subscriber)=> {
        setTimeout(
          () => subscriber.next(this.title), 1
        );
      }
    )
  }

  maximize(): void {
    this.windowManagementService.maximizeWindow(this.windowId);
  }

  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);
  }

  setId(windowId: number): void {
  }

  unfriend() {
    if(this.selectedFriendId !== undefined) {
      this.relationController.unfriendUser(this.selectedFriendId);
      this.selectedFriendId = undefined;
    }
  }

  messageFriend() {

  }

  selectFriend(id: number) {
    this.selectedFriendId = id;
  }

  openProfile(id: number) {
    this.windowManagementService.openWindow({
      windowType: WindowType.UserProfilePage,
      content: {
        userId: id
      }
    });
  }

  selectRequest(id: number) {
    this.selectedUserRequestId = id;
  }
}
