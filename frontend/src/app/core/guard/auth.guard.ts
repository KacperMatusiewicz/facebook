import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public authService: AuthService, public router: Router) {
  }
  canActivate(): boolean {
    if(!this.authService.isAuthenticated()){
      this.router.navigate(['home/login']);
      return false;
    }
    return true;
  }

}
