import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'win-button',
  templateUrl: './win-button.component.html',
  styleUrls: ['./win-button.component.scss']
})
export class WinButtonComponent implements OnInit {

  @Input()
  formGroup!: FormGroup;

  @Input()
  disabled: boolean | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
