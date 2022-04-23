import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'password-input',
  templateUrl: './password-input.component.html',
  styleUrls: ['./password-input.component.scss']
})
export class PasswordInputComponent implements OnInit {

  @Input()
  formGroup!: FormGroup;

  @Input()
  formControlName!: string;

  constructor() { }

  ngOnInit(): void {
  }
}
