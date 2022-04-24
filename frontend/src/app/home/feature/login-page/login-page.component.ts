import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Form, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserLoginData} from "../../service/user-login-data";
import {LoginService} from "../../service/login.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  @Output()
  newAccountRequestEvent = new EventEmitter<any>();

  constructor(private loginService : LoginService) {
  }

  ngOnInit(): void {
  }

  loginUser($event: UserLoginData) {
    this.loginService.loginUser($event).subscribe(response => console.log(response));
  }

  emitNewAccountRequestEvent() {
    this.newAccountRequestEvent.emit();
  }
}
