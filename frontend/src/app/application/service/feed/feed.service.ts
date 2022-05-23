import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor(private httpClient: HttpClient) { }

  public getFeedPostIds() {
    return this.httpClient.get<string[]>("http://localhost:8080/api/v1/feed")
  }
}
