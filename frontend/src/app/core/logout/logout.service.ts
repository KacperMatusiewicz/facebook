import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  logoutUrl : string = "http://localhost:8080/api/v1/auth/logout";

  constructor(private httpClient: HttpClient, private cookieService: CookieService) { }

  logout() {
    return this.httpClient.post(this.logoutUrl, null).subscribe(respone => {
      this.cookieService.delete("applicationAuth", "/");
      window.location.reload();
    });
  }
}
