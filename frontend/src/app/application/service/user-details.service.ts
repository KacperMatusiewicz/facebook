import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserDetails} from "./user-details";
import {Observable} from "rxjs";
import {Post} from "./post";

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  userUrl: string = "http://localhost:8080/api/v1/user/details"
  postUrl: string = "http://localhost:8080/api/v1/post"

  constructor(private httpClient: HttpClient) {}

  getUserDetails() : Observable<UserDetails> {
    return this.httpClient.get<UserDetails>(this.userUrl);
  }

  getUserDetailsBy(userId: number) : Observable<UserDetails> {
    return this.httpClient.get<UserDetails>(this.userUrl + "/" + userId);
  }

  getUserPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.postUrl);
  }

  getPostsBy(userId: number): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.postUrl + "/" + userId);

  }
}
