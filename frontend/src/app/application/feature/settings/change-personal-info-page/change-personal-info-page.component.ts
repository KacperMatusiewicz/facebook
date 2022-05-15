import {AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {Observable} from "rxjs";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SettingsService} from "../settings.service";
import {PersonalInfoDto} from "../personal-info-dto";
import {UserDetailsService} from "../../../service/user-details.service";

@Component({
  selector: 'app-change-personal-info-page',
  templateUrl: './change-personal-info-page.component.html',
  styleUrls: ['./change-personal-info-page.component.scss']
})
export class ChangePersonalInfoPageComponent implements OnInit, DesktopWindow, AfterViewInit {

  icon: string;
  title: string;
  windowId: number;
  style: CSSStyleDeclaration;

  form: FormGroup;

  name = new FormControl('', Validators.required);
  lastName = new FormControl('', Validators.required);
  gender = new FormControl('', Validators.required);



  constructor(
    private fb: FormBuilder,
    private windowService: WindowManagementService,
    private elementRef: ElementRef,
    private settingsService: SettingsService,
    private userDetailsService: UserDetailsService
  ) {
    this.windowId = windowService.getId();
    this.title = "Personal Info Settings";
    this.icon = "assets/icons/change-personal-icon.svg";
    this.style = elementRef.nativeElement.style;

    this.form = fb.group({
      name: this.name,
      lastName: this.lastName,
      gender: this.gender
    });
  }

  ngAfterViewInit(): void {
    this.setCurrentPersonalInfo();
  }


  ngOnInit(): void {
  }

  setCurrentPersonalInfo() {
    this.userDetailsService.getUserDetails().subscribe(
      response => {
        this.name.setValue(response.name);
        this.lastName.setValue(response.lastName);
      },
      error => window.alert(error.error)
    );
  }

  updatePersonalInfo() {
    this.settingsService.changePersonalInfo(
      new PersonalInfoDto(
        this.name.value,
        this.lastName.value,
        this.gender.value
      )
    ).subscribe(
      response => console.log(response),
      error => window.alert(error.error)
    );
  }


  close(): void {
    this.windowService.closeWindow(this.windowId);
  }

  focus(): void {
    this.windowService.focusWindow(this.windowId);
  }

  getIcon(): string | null {
    return this.icon;
  }

  getTitle(): string | Observable<string> {
    return this.title;
  }

  maximize(): void {
    this.windowService.maximizeWindow(this.windowId);
  }

  minimize(): void {
    this.windowService.minimizeWindow(this.windowId);
  }

  setId(windowId: number): void {
  }

}
