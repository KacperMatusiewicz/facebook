import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostCreationRequest} from "./create-post-page/post-creation-request";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  postUrl = "http://localhost:8080/api/v1/post"

  constructor(private httpClient: HttpClient) { }

  createPost(postCreationRequest: PostCreationRequest) {
    return this.httpClient.post(this.postUrl, postCreationRequest);
  }
}
