import { Component, OnInit } from '@angular/core';
import {UserDetailsService} from "../../../../service/user-details.service";
import {UserDetails} from "../../../../service/user-details";

@Component({
  selector: 'app-start-menu-user-profile',
  templateUrl: './start-menu-user-profile.component.html',
  styleUrls: ['./start-menu-user-profile.component.scss']
})
export class StartMenuUserProfileComponent implements OnInit {

  userDetails: UserDetails;

  constructor(private userDetailsService: UserDetailsService) {
    this.userDetails = new UserDetails("", "", "");
    this.userDetailsService.getUserDetails().subscribe(
      (response : UserDetails) => this.userDetails = response,
      error => {window.alert(error.error);}
    )
  }

  ngOnInit(): void {
  }


}
