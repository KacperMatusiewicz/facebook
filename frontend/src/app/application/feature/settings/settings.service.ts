import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PasswordDto} from "./password-dto";
import {PersonalInfoDto} from "./personal-info-dto";
import {ContactInfoDto} from "./contact-info-dto";

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  passwordUrl: string = "http://localhost:8080/api/v1/auth/password";
  contactInfoUrl: string = "http://localhost:8080/api/v1/user/contact";
  private personalInfoUrl = "http://localhost:8080/api/v1/user/personal";

  constructor(private httpClient: HttpClient) { }

  public changePassword(passwordDto: PasswordDto): Observable<any> {
    return this.httpClient.put<PasswordDto>(this.passwordUrl, passwordDto);
  }

  public changePersonalInfo(personalInfo: PersonalInfoDto) {
    return this.httpClient.put<PasswordDto>(this.personalInfoUrl, personalInfo);
  }

  public changeContactInfo(contactInfo: ContactInfoDto) {
    return this.httpClient.put<PasswordDto>(this.contactInfoUrl, contactInfo);
  }
}
