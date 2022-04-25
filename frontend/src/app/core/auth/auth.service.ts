import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private cookieService: CookieService) { }

  public isAuthenticated(): boolean {
    console.log(this.cookieService.get("applicationAuth"));
    if(this.cookieService.check("applicationAuth")) {
      return true;
    } else {
      return false;
    }
  }
}
