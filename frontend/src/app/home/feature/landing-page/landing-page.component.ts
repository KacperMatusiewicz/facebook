import {AfterViewInit, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit, AfterViewInit {

  registerVisibility: boolean;
  mailAlertVisibility: boolean;

  constructor() {
    this.registerVisibility = false;
    this.mailAlertVisibility = false;
  }

  ngAfterViewInit(): void {
        /*let audio = document.createElement("audio");
        audio.src = "assets/sounds/login.mp3";
        audio.volume = 0.5;
        audio.play();*/

    }

  ngOnInit(): void {
  }

  showRegistrationPage() {
    if(!this.registerVisibility) {
      this.registerVisibility = true;
    }
  }

  showMailAlertWindow() {
    if(!this.mailAlertVisibility){
      this.mailAlertVisibility = true;
    }
  }

  closeRegistrationPage() {
    this.registerVisibility = false;
  }

  closeMailAlertWindow() {
    this.mailAlertVisibility = false;
  }

}
