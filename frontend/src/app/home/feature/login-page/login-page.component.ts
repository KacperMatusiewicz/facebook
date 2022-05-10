import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserLoginData} from "../../../core/model/user-login-data";
import {LoginService} from "../../../core/login/login.service";
import {Router} from "@angular/router";
import {SoundService} from "../../../core/sound/sound.service";
import {Sound} from "../../../core/sound/sound";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  @Output()
  newAccountRequestEvent = new EventEmitter<any>();

  constructor(
    private loginService : LoginService,
    private router : Router,
    private soundService: SoundService
  ) {
  }

  ngOnInit(): void {
  }

  loginUser($event: UserLoginData) {
    this.loginService.loginUser($event).subscribe(
      data => {
        this.soundService.playSound(Sound.LOGIN);
        this.router.navigate(['application/home'])
      },
      error => window.alert(error.error)
    );
  }

  emitNewAccountRequestEvent() {
    this.newAccountRequestEvent.emit();
  }
}
