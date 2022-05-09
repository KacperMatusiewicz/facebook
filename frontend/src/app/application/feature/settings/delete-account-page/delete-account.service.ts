import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LogoutService} from "../../../../core/logout/logout.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DeleteAccountService {

  checkCredentialsUrl: string = "http://localhost:8080/api/v1/auth/password"
  deleteAccountUrl: string = "http://localhost:8080/api/v1/user"

  constructor(private httpClient: HttpClient, private logoutService: LogoutService) {
  }

  async deleteAccount(password: string): Promise<any> {
    let deleteAccountObservable = new Promise(response => "xdddd");
    await this.httpClient.get<boolean>(`${this.checkCredentialsUrl}?password=${password}`).subscribe(
      response => {
        if (response) {
          deleteAccountObservable = this.httpClient.delete(this.deleteAccountUrl).toPromise().then(
            res => this.logoutService.logoutAll(),
            error => error.error
          );
        } else {
          deleteAccountObservable = new Promise(() => "Password doesn't match.");
        }
      },
      error => deleteAccountObservable = new Promise<unknown>(() => window.alert(error.error))
    );
    return deleteAccountObservable;
  }
}

