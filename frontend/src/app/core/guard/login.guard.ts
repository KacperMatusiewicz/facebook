import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  constructor(public authService: AuthService, public router: Router) {}

  canActivate(): boolean {
    if(this.authService.isAuthenticated()) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }

}
