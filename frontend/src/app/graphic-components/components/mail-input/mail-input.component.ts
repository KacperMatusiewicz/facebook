import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'mail-input',
  templateUrl: './mail-input.component.html',
  styleUrls: ['./mail-input.component.scss']
})
export class MailInputComponent implements OnInit {

  @Input()
  formGroup!: FormGroup;

  @Input()
  formControlName!: string;

  constructor() { }

  ngOnInit(): void {
  }
}
