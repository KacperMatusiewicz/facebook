import {Component, ElementRef, OnInit} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {SettingsService} from "../settings.service";
import {UserDetailsService} from "../../../service/user-details.service";
import {Observable} from "rxjs";
import {ContactInfoDto} from "../contact-info-dto";

@Component({
  selector: 'app-change-contact-info-page',
  templateUrl: './change-contact-info-page.component.html',
  styleUrls: ['./change-contact-info-page.component.scss']
})
export class ChangeContactInfoPageComponent implements OnInit, DesktopWindow{

  icon: string;
  title: string;
  windowId: number;
  style: CSSStyleDeclaration;

  form: FormGroup;

  email = new FormControl('', Validators.required);

  constructor(
    private fb: FormBuilder,
    private windowService: WindowManagementService,
    private elementRef: ElementRef,
    private settingsService: SettingsService,
    private userDetailsService: UserDetailsService
  ) {
    this.title = "Contact Info Settings";
    this.icon = "assets/icons/change-contact-icon.png";
    this.windowId = windowService.getId();
    this.style = elementRef.nativeElement.style;

    this.form = fb.group({
      email: this.email,
    });
  }

  ngAfterViewInit(): void {
    this.setCurrentContactInfo();
  }


  ngOnInit(): void {
  }

  setCurrentContactInfo() {
    this.userDetailsService.getUserDetails().subscribe(
      response => {
        this.email.setValue(response.email);

      },
      error => window.alert(error.error)
    );
  }

  updateContactInfo() {
    this.settingsService.changeContactInfo(
      new ContactInfoDto(
        this.email.value
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
