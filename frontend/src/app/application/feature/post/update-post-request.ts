export class UpdatePostRequest {

  private postId: number;
  private content: string;

  constructor(postId: number, content: string) {
    this.postId = postId;
    this.content = content;
  }
}
