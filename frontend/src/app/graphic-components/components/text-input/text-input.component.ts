import {Component, Input, OnInit} from '@angular/core';
import {FormGroup, FormGroupDirective} from "@angular/forms";

@Component({
  selector: 'text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.scss'],
})
export class TextInputComponent implements OnInit {

  @Input()
  formGroup!: FormGroup;

  @Input()
  formControlName!: string;
  @Input()
  autocompleteType: string | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
