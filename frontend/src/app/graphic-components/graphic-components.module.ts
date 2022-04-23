import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WindowComponent } from './components/window/window.component';
import { DraggableDirective } from './directives/draggable.directive';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ResizableDirective } from './directives/resizable.directive';
import { TextInputComponent } from './components/text-input/text-input.component';
import { MailInputComponent } from './components/mail-input/mail-input.component';
import { PasswordInputComponent } from './components/password-input/password-input.component';
import { WinButtonComponent } from './components/win-button/win-button.component';
import { CheckboxInputComponent } from './components/checkbox-input/checkbox-input.component';
import { RadioInputComponent } from './components/radio-input/radio-input.component';



@NgModule({
    declarations: [
        WindowComponent,
        DraggableDirective,
        ResizableDirective,
        TextInputComponent,
        MailInputComponent,
        PasswordInputComponent,
        WinButtonComponent,
        CheckboxInputComponent,
        RadioInputComponent
    ],
    exports: [
        WindowComponent,
        DraggableDirective,
        ResizableDirective,
        TextInputComponent,
        MailInputComponent,
        PasswordInputComponent,
        WinButtonComponent,
        CheckboxInputComponent,
        RadioInputComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class GraphicComponentsModule { }
