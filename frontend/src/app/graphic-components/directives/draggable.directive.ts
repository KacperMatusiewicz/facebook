import {AfterViewInit, Directive, ElementRef, Inject, OnDestroy, OnInit} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {fromEvent, Subscription} from "rxjs";
import {takeUntil} from "rxjs/operators";

@Directive({
  selector: 'window[draggable]'
})
export class DraggableDirective implements AfterViewInit, OnDestroy{
  private element : HTMLElement;
  private elementHandle : HTMLElement | undefined;
  private htmlCollection: any;
  private subscriptions: Subscription[] = [];
  private titleElement: HTMLElement | undefined;
  private iconElement: HTMLElement | undefined;
  private titleBarContentElement: HTMLElement | undefined;

  constructor(
    private elementReference : ElementRef,
    @Inject(DOCUMENT) private document: any
  ) {
    this.element = this.elementReference.nativeElement as HTMLElement;
    this.htmlCollection = this.elementReference.nativeElement.getElementsByClassName('title-bar');

  }

  ngAfterViewInit(): void {
        this.elementHandle = this.htmlCollection.item(0) as HTMLElement;
        this.titleElement = this.elementReference.nativeElement.getElementsByClassName('window-icon').item(0);
        this.iconElement = this.elementReference.nativeElement.getElementsByClassName('window-title').item(0);
        this.titleBarContentElement = this.elementReference.nativeElement.getElementsByClassName('title-bar-content').item(0);
        this.initDrag();
  }

  private initDrag() {
    const dragStart$ = fromEvent<MouseEvent>(this.elementHandle as HTMLElement, "mousedown");
    const dragEnd$ = fromEvent<MouseEvent>(this.document, "mouseup");
    const drag$ = fromEvent<MouseEvent>(this.document, "mousemove").pipe(
      takeUntil(dragEnd$)
    );

    let initialX: number,
      initialY: number,
      currentX = Number.parseInt(this.element.style.left.substring(0, this.element.style.left.length-2)),
      currentY = Number.parseInt(this.element.style.top.substring(0, this.element.style.top.length-2));

    let dragSub: Subscription;

    const dragStartSub = dragStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - Number.parseInt(this.element.style.left.substring(0, this.element.style.left.length-2));
      initialY = event.clientY - Number.parseInt(this.element.style.top.substring(0, this.element.style.top.length-2));
      if (
        event.target !== this.elementHandle &&
        event.target !== this.titleElement &&
        event.target !== this.iconElement &&
        event.target !== this.titleBarContentElement
      )
        return;

      this.element.classList.add('free-dragging');
      dragSub = drag$.subscribe((event: MouseEvent) => {
        event.preventDefault();

        currentX = event.clientX - initialX;
        currentY = event.clientY - initialY;

        this.element.style.left = currentX + "px";
        this.element.style.top  = currentY + "px";

        if (currentY < 0){
          this.element.style.top  = 0 + "px";
        }

        for(let i = 0; i<document.body.getElementsByTagName("window").length;i++ ){
          let x = document.body.getElementsByTagName('window').item(i) as HTMLElement;
            x.style.zIndex = "1";
        }
        this.element.style.zIndex = "3";
      });
    });

    const dragEndSub = dragEnd$.subscribe(() => {
      initialX = currentX;
      initialY = currentY;

      this.element.classList.remove('free-dragging');
      if (dragSub) {
        dragSub.unsubscribe();
      }
    });

    this.subscriptions.push.apply(this.subscriptions, [
      dragStartSub,
      dragSub!,
      dragEndSub,
    ]);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((s) => s?.unsubscribe());
  }
}
