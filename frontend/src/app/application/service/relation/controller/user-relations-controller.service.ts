import { Injectable } from '@angular/core';
import {UserRelationsStoreService} from "../store/user-relations-store.service";
import {UserRelationsService} from "../user-relations.service";
import {NotificationDispatcherService} from "../../notification/notification-dispatcher.service";

@Injectable({
  providedIn: 'root'
})
export class UserRelationsControllerService {

  constructor(
    private relationStore: UserRelationsStoreService,
    private relationService: UserRelationsService,
    private notificationDispatcherService: NotificationDispatcherService
  ) { }

  followUser(userId: number) {
    this.relationService.followUser(userId).subscribe(
      response => this.relationStore.addFollowingId(userId),
      error => window.alert(error.error)
    );
  }

  unfollowUser(userId: number) {
    this.relationService.unfollowUser(userId).subscribe(
      response => this.relationStore.removeFollowingId(userId),
      error => window.alert(error.error)
    );
  }

  sendFriendRequest(userId: number) {
    this.relationService.befriendUser(userId).subscribe(
      response => {
        this.relationStore.addRequestedFriendId(userId)
      },
      error => window.alert(error.error)
    );
  }
  acceptFriendRequest(userId: number) {
    this.relationService.acceptFriendRequest(userId).subscribe(
      response => {
        this.relationStore.removeRequestingFriendId(userId);
        this.relationStore.addFriendId(userId);
        this.notificationDispatcherService.deleteNotificationIfExist(userId, "FRIEND_REQUEST");
      },
      error => window.alert(error.error)
    )
  }
  unfriendUser(userId: number) {
    this.relationService.unfriendUser(userId).subscribe(
      response => this.relationStore.removeFriendId(userId),
      error => window.alert(error.error)
    );
  }

  denyFriendRequest(userId: number) {
    this.relationService.denyFriendRequest(userId).subscribe(
      response => {
        this.relationStore.removeRequestingFriendId(userId);
        this.notificationDispatcherService.deleteNotificationIfExist(userId, "FRIEND_REQUEST");
        },
      error => window.alert(error.error)
    );
  }
}
