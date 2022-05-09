import {Component, ElementRef, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {SettingsService} from "../settings.service";
import {LogoutService} from "../../../../core/logout/logout.service";
import {PasswordDto} from "../password-dto";

@Component({
  selector: 'change-password-page',
  templateUrl: './change-password-page.component.html',
  styleUrls: ['./change-password-page.component.scss']
})
export class ChangePasswordPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  private windowId!: number;
  style: CSSStyleDeclaration;
  form: FormGroup;

  oldPassword: FormControl = new FormControl('', Validators.required);
  newPassword: FormControl = new FormControl('', Validators.required);
  confirmNewPassword: FormControl = new FormControl('', Validators.required);

  constructor(
    private fb: FormBuilder,
    private windowManagementService: WindowManagementService,
    private elementRef: ElementRef,
    private settingsService: SettingsService,
    private logoutService: LogoutService
  ) {
    this.icon = "assets/icons/change-password-icon.png";
    this.title = "Change Password";
    this.style = elementRef.nativeElement.style;
    this.windowId = this.windowManagementService.getId();

    this.form = fb.group({
      oldPassword: this.oldPassword,
      newPassword: this.newPassword,
      confirmNewPassword: this.confirmNewPassword,
    });

    this.form.valueChanges.subscribe(
      (f) => {
        const password = f.newPassword;
        const confirm = f.confirmNewPassword;
        if(password !== confirm) {
          this.form.get('confirmNewPassword')?.setErrors({notMatched: true});
        }
        else{
          this.form.get('confirmNewPassword')?.setErrors(null)
        }
      }
    );
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.settingsService.changePassword(new PasswordDto(this.oldPassword.value, this.newPassword.value)).subscribe(
      response => this.logoutService.logout(),
      error => window.alert(error.error)
    );

  }


  close(): void {
    this.windowManagementService.closeWindow(this.windowId);
  }

  focus(): void {
    this.windowManagementService.focusWindow(this.windowId);
  }

  getIcon(): string | null {
    return this.icon;
  }

  getTitle(): string | Observable<string> {
    return this.title;
  }

  maximize(): void {
    this.windowManagementService.maximizeWindow(this.windowId);
  }

  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);
  }

  setId(windowId: number): void {
    this.windowId = windowId;
  }


}
