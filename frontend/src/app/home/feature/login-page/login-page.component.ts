import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Form, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserLoginData} from "../../../core/model/user-login-data";
import {LoginService} from "../../../core/login/login.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  @Output()
  newAccountRequestEvent = new EventEmitter<any>();

  constructor(private loginService : LoginService, private router : Router) {
  }

  ngOnInit(): void {
  }

  loginUser($event: UserLoginData) {
    this.loginService.loginUser($event).subscribe(
      data => {
        let audio = document.createElement("audio");
        audio.src = "assets/sounds/login.mp3";
        audio.volume = 0.5;
        audio.play();
        this.router.navigate(['application/home'])
      },
      error => window.alert(error.error)
    );
  }

  emitNewAccountRequestEvent() {
    this.newAccountRequestEvent.emit();
  }
}
