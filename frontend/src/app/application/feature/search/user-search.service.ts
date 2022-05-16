import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SearchedUserResult} from "./searched-user-result";
import {UserSearchQuery} from "./user-search-query";

@Injectable({
  providedIn: 'root'
})
export class UserSearchService {

  private searchUrl = "http://localhost:8080/api/v1/search"

  constructor(private httpClient: HttpClient) {}

  search(userSearchQuery: UserSearchQuery) {
    return this.httpClient
      .get<SearchedUserResult[]>(this.searchUrl+`?searchedPhrase=${userSearchQuery.searchedPhrase}`);
  }
}
