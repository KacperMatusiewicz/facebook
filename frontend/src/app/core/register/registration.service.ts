import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  registrationUrl: string = "http://localhost:8080/api/v1/user";

  constructor(public httpClient: HttpClient) { }

  registerUser(user: User): Observable<any> {
    return this.httpClient.post(this.registrationUrl, user);
  }
}
