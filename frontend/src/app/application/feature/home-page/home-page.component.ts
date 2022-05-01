import { Component, OnInit } from '@angular/core';
import { LogoutService } from 'src/app/core/logout/logout.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  userProfileWindowVisibility: boolean;

  constructor(private logoutService : LogoutService) {
    this.userProfileWindowVisibility = false;
  }

  ngOnInit(): void {
  }

  logout() {
    this.logoutService.logout();
  }

  userDetailsWindow() {
    if(!this.userProfileWindowVisibility){
      this.userProfileWindowVisibility = true;
    }
  }

  settingsWindow() {

  }
}
