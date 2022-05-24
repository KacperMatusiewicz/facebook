import {Component, OnInit} from '@angular/core';
import {NotificationDto} from "../../../../../service/notification/notification-dto";
import {NotificationDispatcherService} from "../../../../../service/notification/notification-dispatcher.service";
import {WindowManagementService} from "../../../../../service/windowState/window-management.service";
import {WindowType} from "../../../../../service/windowState/window-type";
import {SoundService} from "../../../../../../core/sound/sound.service";
import {Sound} from "../../../../../../core/sound/sound";

@Component({
  selector: 'app-notification-icon',
  templateUrl: './notification-icon.component.html',
  styleUrls: ['./notification-icon.component.scss']
})
export class NotificationIconComponent implements OnInit {

  notifications: NotificationDto[] = [];

  constructor(
    private notificationDispatcher : NotificationDispatcherService,
    private windowService: WindowManagementService,
    private soundService: SoundService,
  ) {
    notificationDispatcher.notificationsSubject.subscribe(
      next => {
        this.notifications = next;
        if (this.notifications.length > 0){
          this.soundService.playSound(Sound.NOTIFICATION);
        }
      }
  );

  }

  ngOnInit(): void {
  }

  openNotificationList(){
    this.windowService.openWindow({windowType: WindowType.NotificationPage})
  }
}
