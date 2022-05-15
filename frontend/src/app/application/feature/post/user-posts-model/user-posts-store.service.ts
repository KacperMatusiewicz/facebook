import { Injectable } from '@angular/core';
import {Post} from "../../../service/post";
import {BehaviorSubject} from "rxjs";
import {PostService} from "../post.service";

@Injectable({
  providedIn: 'root'
})
export class UserPostsStoreService {

  private _posts: Post[] = [];

  userPostsObservable : BehaviorSubject<Post[]>;

  constructor(private postService: PostService) {
    this.userPostsObservable = new BehaviorSubject<Post[]>(this._posts);
    this.initPosts();
  }

  private initPosts() {
    this.postService.getUserPosts().subscribe(
      response => {
        this._posts = response;
        this.userPostsObservable.next(this._posts);
      }
    );
  }

  get posts(): Post[] {
    return this._posts;
  }

  set posts(value: Post[]) {
    this._posts = value;
    this.userPostsObservable.next(this._posts);
  }

  addPost(post :Post) {
    this._posts.unshift(post);
    this.userPostsObservable.next(this._posts);
  }

  removePost(id: number) {
    let index = this._posts.findIndex(
      post => post.id === id
    );
    this._posts.splice(index, 1);
  }
}
