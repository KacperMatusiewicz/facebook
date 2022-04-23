import {Component, Input, OnInit} from '@angular/core';
import {FormControlName, FormGroup} from "@angular/forms";

@Component({
  selector: 'checkbox-input',
  templateUrl: './checkbox-input.component.html',
  styleUrls: ['./checkbox-input.component.scss']
})
export class CheckboxInputComponent implements OnInit {

  @Input()
  formControlName!: string;

  @Input()
  formGroup!: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
