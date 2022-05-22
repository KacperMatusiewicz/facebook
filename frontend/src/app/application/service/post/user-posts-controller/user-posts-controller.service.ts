import { Injectable } from '@angular/core';
import {UserPostsStoreService} from "../user-posts-model/user-posts-store.service";
import {Post} from "../../post";
import {PostService} from "../post.service";
import {PostCreationRequest} from "../../../feature/post/create-post-page/post-creation-request";
import {Observable} from "rxjs";
import {UpdatePostRequest} from "../update-post-request";

@Injectable({
  providedIn: 'root'
})
export class UserPostsControllerService {

  constructor(private userPostsStore: UserPostsStoreService, private postService: PostService) { }

  async addPost(postRequest: PostCreationRequest): Promise<any>{
    await this.postService.createPost(postRequest).subscribe(
      (response: Post) => {
        this.userPostsStore.addPost(response);
        return new Observable(subscriber => subscriber.complete()).toPromise();
      },
      error => {
        return new Observable(subscriber => {subscriber.error()});
      }
    );
  }

  removePost(id: number) {
    this.postService.deletePost(id).subscribe(
      response => this.userPostsStore.removePost(id),
      (error) => window.alert(error.error)
    );
  }

  async editPost(postRequest: UpdatePostRequest): Promise<any>{
    await this.postService.editPost(postRequest).subscribe(
      (response: Post) => {
        this.userPostsStore.updatePost(response);
        return new Observable(subscriber => subscriber.complete()).toPromise();
      },
      error => {
        return new Observable(subscriber => {subscriber.error()});
      }
    );
  }

  getPostById(postId: number) {
    let post = this.userPostsStore.getPostById(postId);
    console.log(post);
    return post;
  }
}
