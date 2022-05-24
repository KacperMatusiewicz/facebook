import { Injectable } from '@angular/core';
import {NotificationService} from "./notification.service";
import {UserRelationsStoreService} from "../relation/store/user-relations-store.service";
import {NotificationDto} from "./notification-dto";
import {BehaviorSubject} from "rxjs";
import {not} from "rxjs/internal-compatibility";

@Injectable({
  providedIn: 'root'
})
export class NotificationDispatcherService {

  notificationsList: NotificationDto[] = [];
  notificationsSubject: BehaviorSubject<NotificationDto[]>;


  constructor(
    private notificationService: NotificationService,
    private relationStore: UserRelationsStoreService,
  ) {
    this.notificationsSubject = new BehaviorSubject<NotificationDto[]>(this.notificationsList);

    notificationService.getInitialNotifications().subscribe(
      response => {
        response.forEach((notification) => this.dispatchNotification(notification))
        this.notificationsSubject.next(this.notificationsList);
      }
    );

    this.notificationService.subscribeToIncomingNotifications().subscribe({
      next: value => {
        this.dispatchNotification(value);
        this.notificationsSubject.next(this.notificationsList);
      }
    });
  }

  dispatchNotification(notificationDto : NotificationDto) {
    console.log("notif dispatch: " + notificationDto.notificationType)
    //console.table(notificationDto);
    switch (notificationDto.notificationType) {
      case "FRIEND_REQUEST":
        this.relationStore.addRequestingFriendId(notificationDto.relatedId);
        this.notificationsList.push(notificationDto);
        break;
      case "FRIEND_APPROVAL":
        console.log("jestem tu " + notificationDto.notificationType);
        this.relationStore.addFriendId(notificationDto.relatedId);
        this.notificationsList.push(notificationDto);
        break;
      case "MESSAGE":
        this.notificationsList.push(notificationDto);
        break;
      case "FRIEND_REMOVED":
        this.relationStore.removeFriendId(notificationDto.relatedId);
        this.deleteNotificationIfExist(notificationDto.relatedId, notificationDto.notificationType);
        break;
    }
  }

  deleteNotificationIfExist(relatedId: number, notificationType: string) {
    this.notificationsList.filter((notification) => {
      return notification.relatedId === relatedId && notification.notificationType === notificationType
    })
    .forEach((notification) => {
      this.notificationService.deleteNotification(notification).subscribe();
        let index = this.notificationsList.indexOf(notification);
        this.notificationsList.splice(index, 1);
        this.notificationsSubject.next(this.notificationsList);
    });
  }
}
