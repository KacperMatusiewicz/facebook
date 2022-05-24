import {Component, ElementRef, OnInit} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {Observable} from "rxjs";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {NotificationDto} from "../../../service/notification/notification-dto";
import {NotificationDispatcherService} from "../../../service/notification/notification-dispatcher.service";
import {SoundService} from "../../../../core/sound/sound.service";
import {Sound} from "../../../../core/sound/sound";

@Component({
  selector: 'app-notifications-page',
  templateUrl: './notifications-page.component.html',
  styleUrls: ['./notifications-page.component.scss']
})
export class NotificationsPageComponent implements OnInit, DesktopWindow {

  title: string;
  style: CSSStyleDeclaration;
  windowId: number;

  notificationList: NotificationDto[] = [];

  constructor(
    private elemRef: ElementRef,
    private windowService: WindowManagementService,
    private notificationDispatcher: NotificationDispatcherService,
  ) {
    this.title = "Notifications";
    this.style = this.elemRef.nativeElement.style;
    this.windowId = windowService.getId();

    notificationDispatcher.notificationsSubject.subscribe({
      next: value => {
        this.notificationList = value;
      }
    })

  }

  ngOnInit(): void {

  }

  close(): void {
    this.windowService.closeWindow(this.windowId);
  }

  focus(): void {
    this.windowService.focusWindow(this.windowId);
  }

  getIcon(): string | null {
    return "/assets/icons/warning.svg";
  }

  getTitle(): string | Observable<string> {
    return this.title;
  }

  maximize(): void {
  }

  minimize(): void {
  }

  setId(windowId: number): void {
  }

  notificationAction(notification: NotificationDto){
   console.log("handle me reeeeeeeeee");
    console.table(notification);
  }

  removeNotification(notification: NotificationDto){
    this.notificationDispatcher.deleteNotificationIfExist(notification.relatedId, notification.notificationType);
  }

}
