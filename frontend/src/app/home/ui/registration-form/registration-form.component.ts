import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import { User } from '../../user';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss']
})
export class RegistrationFormComponent implements OnInit {
  @Output()
  userRegistrationDataEmitter = new EventEmitter<User>();

  registrationForm: FormGroup;

  name = new FormControl('', Validators.required);
  lastName = new FormControl('', Validators.required);
  email = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);
  confirmPassword = new FormControl('', Validators.required)
  userAgreement = new FormControl('', Validators.required);

  constructor(fb: FormBuilder) {
    this.registrationForm = fb.group(
      {
        name: this.name,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        confirmPassword: this.confirmPassword,
        userAgreement: this.userAgreement,
      }
    );

    this.registrationForm.valueChanges.subscribe(
      (f) => {
        const password = f.password;
        const confirm = f.confirmPassword;
        if(password !== confirm) {
          this.registrationForm.get('confirmPassword')?.setErrors({notMatched: true});
        }
        else{
          this.registrationForm.get('confirmPassword')?.setErrors(null)
        }
      }
    );
  }

  ngOnInit(): void {
  }
  registerClient(): void {
    this.userRegistrationDataEmitter.emit(new User(
      this.name.value,
      this.lastName.value,
      this.email.value,
      this.password.value
    ));
  }
}
