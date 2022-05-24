export class NotificationDto {

  userId: number;
  content: string;
  relatedId: number;
  notificationType: string;

  constructor(userId: number, content: string, relatedId: number, notificationType: string) {
    this.userId = userId;
    this.content = content;
    this.relatedId = relatedId;
    this.notificationType = notificationType;
  }
}
