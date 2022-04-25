import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from "../../../core/model/user";
import {RegistrationService} from "../../../core/register/registration.service";
import {Router} from "@angular/router";
import {state} from "@angular/animations";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  @Output()
  closeWindowEvent = new EventEmitter<any>();
  @Output()
  registrationMailSendEvent = new EventEmitter<any>();

  constructor(public registrationService: RegistrationService, public router: Router) { }

  ngOnInit(): void {
  }

  receiveRegistrationDataAndSend($event: User) {
    this.registrationService.registerUser($event).subscribe(
      (data) => {
        this.emitCloseWindowEvent();
        this.emitRegistrationMailSendEvent()
      },
      (error) => this.showErrorMessage(error.error)
      );
  }

  showErrorMessage(message: string) {
    window.alert(message)
  }

  emitCloseWindowEvent() {
    this.closeWindowEvent.emit();
  }

  private emitRegistrationMailSendEvent() {
    this.registrationMailSendEvent.emit();
  }
}
