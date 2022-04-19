import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WindowComponent } from './components/window/window.component';
import { DraggableDirective } from './directives/draggable.directive';
import {FormsModule} from "@angular/forms";
import { ResizableDirective } from './directives/resizable.directive';



@NgModule({
    declarations: [
        WindowComponent,
        DraggableDirective,
        ResizableDirective
    ],
  exports: [
    WindowComponent,
    DraggableDirective,
    ResizableDirective
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class GraphicComponentsModule { }
