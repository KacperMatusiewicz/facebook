import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UsersIds} from "./users-ids";

@Injectable({
  providedIn: 'root'
})
export class UserRelationsService {

  private followCommandUrl: string = "http://localhost:8080/api/v1/relation/follow";
  private friendCommandUrl: string = "http://localhost:8080/api/v1/relation/friend";

  private followQueryUrl: string = "http://localhost:8080/api/v1/relation/followers";
  private friendQueryUrl: string = "http://localhost:8080/api/v1/relation/friends";

  constructor(private httpClient: HttpClient) { }

  getAllOwnFriends() {
    return this.httpClient.get<UsersIds>(this.friendQueryUrl+"/self");
  }

  getAllOwnFollowers() {
    return this.httpClient.get<UsersIds>(this.followQueryUrl+"/self");
  }

  getAllOwnFollowings() {
    return this.httpClient.get<UsersIds>("http://localhost:8080/api/v1/relation/followings/self");
  }

  followUser(userId: number){
    return this.httpClient.post(this.followCommandUrl, {targetId: userId});
  }

  befriendUser(userId: number){
    return this.httpClient.post(this.friendCommandUrl+"/request", {targetId: userId});
  }

  unfollowUser(userId: number){
    return this.httpClient.delete(this.followCommandUrl+"?targetId="+userId);
  }

  unfriendUser(userId: number){
    return this.httpClient.delete(this.friendCommandUrl+"?targetId="+userId);
  }

  getAllOwnSentFriendRequests() {
    return this.httpClient.get<UsersIds>("http://localhost:8080/api/v1/relation/requests/sent/self");
  }
  getAllOwnReceivedFriendRequests() {
    return this.httpClient.get<UsersIds>("http://localhost:8080/api/v1/relation/requests/received/self");
  }

  acceptFriendRequest(userId: number) {
    return this.httpClient.post(
      "http://localhost:8080/api/v1/relation/friend/response",
      {targetId: userId, responseType: "ACCEPT"}
    );
  }

  denyFriendRequest(userId: number) {
    return this.httpClient.post(
      "http://localhost:8080/api/v1/relation/friend/response",
      {targetId: userId, responseType: "IGNORE"}
    );
  }
}
