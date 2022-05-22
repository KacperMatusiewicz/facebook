import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {UserRelationsService} from "../user-relations.service";

@Injectable({
  providedIn: 'root'
})
export class UserRelationsStoreService {

  private friendsIds: number[] = [];
  private followersIds: number[] = [];
  private followingsIds: number[] = [];
  private sentFriendsRequests: number[] = [];
  private receivedFriendsRequest: number[] = [];

  followersSubject: BehaviorSubject<number[]>;
  followingsSubject: BehaviorSubject<number[]>;
  friendsSubject: BehaviorSubject<number[]>;
  sentFriendRequestsSubject: BehaviorSubject<number[]>;
  receivedFriendRequestsSubject: BehaviorSubject<number[]>;

  constructor(private relationService: UserRelationsService) {
    this.followersSubject = new BehaviorSubject<number[]>(this.followersIds);
    this.followingsSubject = new BehaviorSubject<number[]>(this.followingsIds);
    this.friendsSubject = new BehaviorSubject<number[]>(this.friendsIds);
    this.sentFriendRequestsSubject = new BehaviorSubject<number[]>(this.sentFriendsRequests);
    this.receivedFriendRequestsSubject = new BehaviorSubject<number[]>(this.receivedFriendsRequest);
    this.relationService.getAllOwnFollowers().subscribe(
      response =>{
        this.followersIds = response.groupIds;
        this.followersSubject.next(this.followersIds);
      },
      error => window.alert(error.error)
    );
    this.relationService.getAllOwnFriends().subscribe(
      response =>{
        this.friendsIds = response.groupIds;
        this.friendsSubject.next(this.friendsIds);
      },
      error => window.alert(error.error)
    );
    this.relationService.getAllOwnSentFriendRequests().subscribe(
      response => {
        this.sentFriendsRequests = response.groupIds;
        this.sentFriendRequestsSubject.next(this.sentFriendsRequests);
        },
      error => window.alert(error.error)
    );
    this.relationService.getAllOwnReceivedFriendRequests().subscribe(
      response => {
        this.receivedFriendsRequest = response.groupIds;
        this.receivedFriendRequestsSubject.next(this.receivedFriendsRequest);
      },
      error => window.alert(error.error)
    );
    this.relationService.getAllOwnFollowings().subscribe(
      response => {
        this.followingsIds = response.groupIds;
        this.followingsSubject.next(this.followingsIds);
      },
      error => window.alert(error.error)
    );
  }

  addFriendId(id: number){
    this.friendsIds.push(id);
    this.friendsSubject.next(this.friendsIds);
  }
  removeFriendId(id: number){
    let index: number = this.friendsIds.findIndex((friendId: number) => friendId === id);
    console.log(this.friendsIds);
    this.friendsIds.splice(index, 1);
    console.log("jestem wywolywany")
    console.log(this.friendsIds);
    this.friendsSubject.next(this.friendsIds);

  }
  addFollowerId(id: number){
    this.followersIds.push(id);
    this.followersSubject.next(this.followersIds);
  }
  removeFollowerId(id: number){
    let index: number = this.followersIds.findIndex((followerId: number) => followerId === id);
    this.followersIds.splice(index, 1);
    this.followersSubject.next(this.followersIds);
  }
  addFollowingId(id: number){
    this.followingsIds.push(id);
    this.followingsSubject.next(this.followingsIds);
  }
  removeFollowingId(id: number){
    let index: number = this.followingsIds.findIndex((followerId: number) => followerId === id);
    this.followingsIds.splice(index, 1);
    this.followingsSubject.next(this.followingsIds);
  }
  addRequestedFriendId(id: number){
    this.sentFriendsRequests.push(id);
    this.sentFriendRequestsSubject.next(this.sentFriendsRequests);
  }
  removeRequestedFriendId(id: number){
    let index: number = this.sentFriendsRequests.findIndex((followerId: number) => followerId === id);
    this.sentFriendsRequests.splice(index, 1);
    this.sentFriendRequestsSubject.next(this.sentFriendsRequests);
  }
  addRequestingFriendId(id: number){
    this.receivedFriendsRequest.push(id);
    this.receivedFriendRequestsSubject.next(this.receivedFriendsRequest);
  }
  removeRequestingFriendId(id: number){
    let index: number = this.receivedFriendsRequest.findIndex((followerId: number) => followerId === id);
    this.receivedFriendsRequest.splice(index, 1);
    this.receivedFriendRequestsSubject.next(this.receivedFriendsRequest);
  }

  checkIfCanSendFriendRequest(userId : number): boolean {
    let result = this.friendsIds.find((el => userId === el));
    let result2 = this.sentFriendsRequests.find((el => userId === el));
    let result3 = this.receivedFriendsRequest.find((el => userId === el));
    if (result === undefined && result2 === undefined && result3 === undefined){
      return true;
    }
    return false;
  }
  checkIfCanRemoveFriend(userId : number): boolean {
    let result = this.friendsIds.find((el => userId === el));
    if (result !== undefined){
      return true;
    }
    return false;
  }
  checkIfCanFollow(userId : number): boolean {
    let result = this.friendsIds.find((el => userId === el));
    let result2 = this.followingsIds.find((el => userId === el));
    if (result === undefined && result2 === undefined){
      return true;
    }
    return false;
  }
  checkIfCanUnfollow(userId : number): boolean {
    let result = this.friendsIds.find((el => userId === el));
    let result2 = this.followingsIds.find((el => userId === el));
    if (result === undefined && result2 !== undefined){
      return true;
    }
    return false;
  }
}
