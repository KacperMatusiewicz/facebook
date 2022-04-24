import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  registerVisibility: boolean;

  constructor() {
    this.registerVisibility = false;
  }

  ngOnInit(): void {
  }

  showRegistrationPage() {
    if(!this.registerVisibility) {
      this.registerVisibility = true;
    }
  }

  closeRegistrationPage() {
    this.registerVisibility = false;
  }
}
