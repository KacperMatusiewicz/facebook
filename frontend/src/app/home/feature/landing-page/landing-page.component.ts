import {Component, OnInit} from '@angular/core';
import {SoundService} from "../../../core/sound/sound.service";
import {Sound} from "../../../core/sound/sound";

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  registerVisibility: boolean;
  mailAlertVisibility: boolean;

  constructor(private soundService: SoundService) {
    this.registerVisibility = false;
    this.mailAlertVisibility = false;
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
      this.soundService.playSound(Sound.NOTIFICATION);

    }
  }

  closeRegistrationPage() {
    this.registerVisibility = false;
  }

  closeMailAlertWindow() {
    this.mailAlertVisibility = false;
  }

}
