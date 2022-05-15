export class Post {
  id!: number | undefined;
  content: string;
  creationDate: string;

  constructor(content: string, creationDate: string) {
    this.content = content;
    this.creationDate = creationDate;
  }
}
