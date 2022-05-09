import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PasswordDto} from "./password-dto";

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  passwordUrl: string = "http://localhost:8080/api/v1/auth/password";

  constructor(private httpClient: HttpClient) { }

  public changePassword(passwordDto: PasswordDto): Observable<any> {
    return this.httpClient.put<PasswordDto>(this.passwordUrl, passwordDto);
  }
}
