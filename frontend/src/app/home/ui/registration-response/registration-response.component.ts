import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-confirmation',
  templateUrl: './registration-response.component.html',
  styleUrls: ['./registration-response.component.scss']
})
export class RegistrationResponseComponent implements OnInit {

  constructor(private router: Router) {
    console.log(history.state)
    console.log(history.state.message);
  }

  ngOnInit(): void {
  }

}
