import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserLoginData} from "../../../../core/model/user-login-data";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  loginForm: FormGroup;

  email = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);

  @Output()
  userLoginData = new EventEmitter<UserLoginData>();

  @Output()
  createNewAccountRequestEvent = new EventEmitter<any>();

  constructor(public fb : FormBuilder) {
    this.loginForm = fb.group({
      email: this.email,
      password: this.password
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.userLoginData.emit(new UserLoginData(this.email.value, this.password.value));
  }

  onCreateNewAccount() {
    this.createNewAccountRequestEvent.emit();
  }
}
