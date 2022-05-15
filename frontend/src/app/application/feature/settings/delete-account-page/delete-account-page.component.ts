import {Component, ElementRef, OnInit} from '@angular/core';
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {Observable} from "rxjs";
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {DeleteAccountService} from "./delete-account.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-delete-account-page',
  templateUrl: './delete-account-page.component.html',
  styleUrls: ['./delete-account-page.component.scss']
})
export class DeleteAccountPageComponent implements OnInit, DesktopWindow {

  icon: string;
  title: string;
  style: CSSStyleDeclaration;
  form: FormGroup;

  confirmation = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);
  private windowId: number;

  constructor(
    private fb: FormBuilder,
    private windowManagementService: WindowManagementService,
    private elementRef: ElementRef,
    private deleteAccountService: DeleteAccountService
  ) {
    this.icon = "assets/icons/trash-icon.png"
    this.title = "Delete Account"
    this.style = elementRef.nativeElement.style;
    this.windowId = windowManagementService.getId();

    this.form = fb.group({
      confirmation: this.confirmation,
      password: this.password
    });

  }

  ngOnInit(): void {
  }

  close(): void{
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
    this.windowId= windowId;
  }

  deleteAccount() {
    this.deleteAccountService.deleteAccount(this.password.value);
  }

}
