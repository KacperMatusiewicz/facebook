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

  form: FormGroup;

  name = new FormControl('', Validators.required);
  lastName = new FormControl('', Validators.required);
  email = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);
  confirmPassword = new FormControl('', Validators.required)
  userAgreement = new FormControl('', Validators.required);
  gender = new FormControl('', Validators.required);

  constructor(fb: FormBuilder) {
    this.form = fb.group(
      {
        name: this.name,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        passwordConfirmation: this.confirmPassword,
        userAgreement: this.userAgreement,
        gender: this.gender
      }
    );

    this.form.valueChanges.subscribe(
      (f) => {
        const password = f.password;
        const confirm = f.confirmPassword;
        if(password !== confirm) {
          this.form.get('confirmPassword')?.setErrors({notMatched: true});
        }
        else{
          this.form.get('confirmPassword')?.setErrors(null)
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
      this.gender.value,
      this.password.value
    ));
  }
}
