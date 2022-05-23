export class Post {
  id!: number | undefined;
  content: string;
  creationDate: string;
  userId?: number;

  constructor(content: string, creationDate: string, userId?: number) {
    this.content = content;
    this.creationDate = creationDate;
    this.userId = userId;
  }
}
