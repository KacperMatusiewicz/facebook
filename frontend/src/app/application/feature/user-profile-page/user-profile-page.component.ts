import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserDetails} from "../../service/user-details";
import {UserDetailsService} from "../../service/user-details.service";
import {Post} from "../../service/post";

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit {

  @Input()
  userId: number | undefined;

  @Output()
  closeWindowEvent = new EventEmitter<any>();

  userDetails: UserDetails;
  posts : Post[];

  constructor(private userDetailsService: UserDetailsService) {
    this.userDetails = new UserDetails("", "", "")
    this.posts = [];
    this.setUserDetail();
    this.setPosts();
  }

  private setUserDetail() {
    if (this.userId === undefined) {
      this.userDetailsService.getUserDetails().subscribe(
        (response) => this.userDetails = response,
        (error) => window.alert(error.error)
      );
    } else {
      this.userDetailsService.getUserDetailsBy(this.userId).subscribe(
        (response) => this.userDetails = response,
        (error) => window.alert(error.error)
      );
    }
  }

  ngOnInit(): void {
  }

  emitCloseWindowEvent() {
    this.closeWindowEvent.emit();
  }

  private setPosts() {
    if (this.userId === undefined) {
      this.userDetailsService.getUserPosts().subscribe(
        (response) => this.posts = response,
        (error) => window.alert(error.error)
      );
    } else {
      this.userDetailsService.getPostsBy(this.userId).subscribe(
        (response) => this.posts = response,
        (error) => window.alert(error.error)
      );
    }
  }

  public getFormattedDate(date: string): string{
    let dateFormat: Date = new Date(date);
    let timeF = new Intl.DateTimeFormat('pl-PL', {hour: '2-digit', minute: '2-digit'});
    let dateF = new Intl.DateTimeFormat('pl-PL', {day: '2-digit', month: '2-digit', year: 'numeric'});
    return `${dateF.format(dateFormat)} ${timeF.format(dateFormat)}`;
  }
}
