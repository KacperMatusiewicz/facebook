import { Component, OnInit } from '@angular/core';
import { LogoutService } from 'src/app/core/logout/logout.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(private logoutService : LogoutService) { }

  ngOnInit(): void {
  }

  logout() {
    this.logoutService.logout();
  }
}
