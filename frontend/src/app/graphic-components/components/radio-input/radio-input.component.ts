import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'radio-input',
  templateUrl: './radio-input.component.html',
  styleUrls: ['./radio-input.component.scss']
})
export class RadioInputComponent implements OnInit {

  @Input()
  formControlName!: string;

  @Input()
  formGroup!: FormGroup;

  @Input()
  value! : string;

  constructor() {
  }

  ngOnInit(): void {
  }

}
