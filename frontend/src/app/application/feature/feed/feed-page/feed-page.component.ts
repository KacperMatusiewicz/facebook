import {Component, ElementRef, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {FeedInfoStoreService} from "../../../service/feed/store/feed-info-store.service";
import {Post} from "../../../service/post";
import {UserDetails} from "../../../service/user-details";
import {UserDetailsService} from "../../../service/user-details.service";
import {WindowType} from "../../../service/windowState/window-type";

@Component({
  selector: 'app-feed-page',
  templateUrl: './feed-page.component.html',
  styleUrls: ['./feed-page.component.scss']
})
export class FeedPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  windowId: number;
  style: CSSStyleDeclaration;

  feedPosts: Post[] = [];
  postAuthors: UserDetails[] = [];
  private copyInitFeedPostListSubscription;
  public readonly copyFeedPostSubject = new BehaviorSubject<Post[]>([]);
  constructor(
    private windowService: WindowManagementService,
    private elementRef: ElementRef,
    private feedInfoStore: FeedInfoStoreService,
    private userDetailsService: UserDetailsService
  ) {
    this.icon = "assets/icons/feed-icon.png";
    this.title = "Feed";
    this.windowId = windowService.getId();
    this.style = elementRef.nativeElement.style;
    this.copyInitFeedPostListSubscription = feedInfoStore.initFeedPostsListSubject.subscribe({
      next: (value: Post[]) => {
        this.copyFeedPostSubject.next(value);
      }
    });
    this.copyFeedPostSubject.subscribe({
      next: value => {
        this.feedPosts = value;
        for(let i = 0; i < this.feedPosts.length; i++) {
          let userId = value[i].userId;
          if(userId !== undefined) {
            this.userDetailsService.getUserDetailsBy(userId).subscribe(
              (response: UserDetails) => {
                this.postAuthors[i] = response;
              }
            );
          }
        }
        this.copyInitFeedPostListSubscription.unsubscribe();
      }
    })
    feedInfoStore.feedPostsSubject.subscribe({
      next: (value: Post) => {
        let index = this.feedPosts.push(value) - 1;
        let userId = value.userId;
        if (userId !== undefined) {
          this.userDetailsService.getUserDetailsBy(userId).subscribe(
            (response: UserDetails) => {
              this.postAuthors[index] = response;
            }
          );
        }
      }
    });
  }

  getIcon(): string | null {
    return this.icon;
  }

  getTitle(): string | Observable<string> {
      return new Observable((subscriber) => {
        setTimeout(() => {
          subscriber.next(this.title);
        }, 100);
      });
  }

  close(): void {
      this.windowService.closeWindow(this.windowId);
  }

  focus(): void {
      this.windowService.focusWindow(this.windowId);
  }

  maximize(): void {
    this.windowService.maximizeWindow(this.windowId);
  }

  minimize(): void {
    this.windowService.minimizeWindow(this.windowId);
  }

  setId(windowId: number): void {
    this.windowId = windowId;
  }

  ngOnInit(): void {
  }

  openAuthorProfilePage(userId: number | undefined){
    if (userId === undefined)
      return;

    this.windowService.openWindow({
      windowType: WindowType.UserProfilePage,
      content: {
        userId: userId
      }
    });
  }
  public getFormattedDate(date: string): string{
    let dateFormat: Date = new Date(date);
    let timeF = new Intl.DateTimeFormat('pl-PL', {hour: '2-digit', minute: '2-digit'});
    let dateF = new Intl.DateTimeFormat('pl-PL', {day: '2-digit', month: '2-digit', year: 'numeric'});
    return `${dateF.format(dateFormat)} ${timeF.format(dateFormat)}`;
  }

  public formatAuthorName(userDetails: UserDetails): String{
    return `${userDetails.name} ${userDetails.lastName}`;
  }
}
