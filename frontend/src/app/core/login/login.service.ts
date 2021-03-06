import { Injectable } from '@angular/core';
import {UserLoginData} from "../model/user-login-data";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginUrl : string = "http://localhost:8080/api/v1/auth/login"

  constructor(private httpClient : HttpClient) { }

  loginUser(userLoginData: UserLoginData) {
    return this.httpClient.post<UserLoginData>(this.loginUrl, userLoginData);
  }
}
