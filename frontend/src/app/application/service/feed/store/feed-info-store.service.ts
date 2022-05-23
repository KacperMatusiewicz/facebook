import { Injectable } from '@angular/core';
import {FeedService} from "../feed.service";
import {Post} from "../../post";
import {PostService} from "../../post/post.service";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FeedInfoStoreService {

  private feedPostsIds: string[] = [];
  private feedPosts: Post[] = [];
  initFeedPostsListSubject: BehaviorSubject<Post[]>;
  feedPostsSubject: Subject<Post>;

  constructor(
    private feedService: FeedService,
    private postService: PostService
  ) {
    this.initFeedPostsListSubject = new BehaviorSubject<Post[]>([]);
    this.feedPostsSubject = new Subject<Post>();
    feedService.getFeedPostIds().subscribe(
      (response) => {
        console.log("response")
        console.log(response);
        this.feedPostsIds = response;
        this.feedPostsIds.forEach(
          (id) => {
            this.postService.getPostById(parseInt(id)).subscribe(
              response => {
                this.feedPostsSubject.next(response);
                this.feedPosts.push(response);
                this.initFeedPostsListSubject.next(this.feedPosts);
                console.log(this.feedPosts);
              }
            );
          }
        );
      },
      error => {
        window.alert(error.error);
      }
    );
  }
}
