import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";
import {NotificationDto} from "./notification-dto";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private httpClient: HttpClient) { }

  public getInitialNotifications() {
    return this.httpClient.get<NotificationDto[]>("api/v1/notification/initial/self");
  }

  public subscribeToIncomingNotifications() {
    let eventSource = new EventSource("http://localhost:8080/api/v1/notification/subscribe");
    let subscription = new Subject<NotificationDto>();
    eventSource.addEventListener("message", ev => {
      subscription.next(JSON.parse(ev.data));
    });
    return subscription;
  }

  public deleteNotification(notificationDto: NotificationDto){
    return this.httpClient.delete("api/v1/notification/delete",{body: notificationDto});
  }
}
