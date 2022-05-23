import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostCreationRequest} from "../../feature/post/create-post-page/post-creation-request";
import {Post} from "../post";
import {Observable} from "rxjs";
import {UpdatePostRequest} from "./update-post-request";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  postUrl = "http://localhost:8080/api/v1/post"
  deletePostUrl = "http://localhost:8080/api/v1/post/"
  editPostUrl = "http://localhost:8080/api/v1/post/edit"

  constructor(private httpClient: HttpClient) { }

  createPost(postCreationRequest: PostCreationRequest) {
    return this.httpClient.post<Post>(this.postUrl, postCreationRequest);
  }

  deletePost(id: number){
    return this.httpClient.delete(this.deletePostUrl+id);
  }

  getUserPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.postUrl);
  }

  getPostsBy(userId: number): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.postUrl + "/" + userId);
  }

  getPostById(postId: number): Observable<Post> {
    return this.httpClient.get<Post>(this.postUrl+"/id?postId="+postId);
  }

  editPost(updatePostRequest: UpdatePostRequest){
    return this.httpClient.put<Post>(this.editPostUrl, updatePostRequest);
  }
}
