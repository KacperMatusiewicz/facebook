import {AfterViewInit, Directive, ElementRef, Inject, OnDestroy} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {fromEvent, Subscription} from "rxjs";
import {takeUntil} from "rxjs/operators";

@Directive({
  selector: 'window[resizable]'
})
export class ResizableDirective implements AfterViewInit, OnDestroy{


  element: HTMLElement;
  htmlCollection: HTMLCollection;

  topHandle: HTMLElement | undefined;
  leftHandle: HTMLElement | undefined;
  rightHandle: HTMLElement | undefined;
  bottomHandle: HTMLElement | undefined;
  topLeftHandle: HTMLElement | undefined;
  topRightHandle: HTMLElement | undefined;
  bottomLeftHandle: HTMLElement | undefined;
  bottomRightHandle: HTMLElement | undefined;

  resizingMethod: any;

  private subscriptions: Subscription[] = [];

  constructor(
    private elementReference : ElementRef,
    @Inject(DOCUMENT) private document: any
  ) {
    this.element = this.elementReference.nativeElement as HTMLElement;
    this.htmlCollection = this.elementReference.nativeElement.getElementsByClassName('window-resize-anchor');
  }

  ngAfterViewInit(): void {
    this.topHandle = this.htmlCollection.item(4) as HTMLElement;
    this.leftHandle = this.htmlCollection.item(6) as HTMLElement;
    this.rightHandle = this.htmlCollection.item(7) as HTMLElement;
    this.bottomHandle = this.htmlCollection.item(5) as HTMLElement;
    this.bottomLeftHandle = this.htmlCollection.item(2) as HTMLElement;
    this.bottomRightHandle = this.htmlCollection.item(3) as HTMLElement;
    this.topRightHandle = this.htmlCollection.item(1) as HTMLElement;
    this.topLeftHandle = this.htmlCollection.item(0) as HTMLElement;

    this.initResize();
  }

  initResize() {
    const leftResizeStart$ = fromEvent<MouseEvent>(this.leftHandle as HTMLElement, "mousedown");
    const rightResizeStart$ = fromEvent<MouseEvent>(this.rightHandle as HTMLElement, "mousedown");
    const topResizeStart$ = fromEvent<MouseEvent>(this.topHandle as HTMLElement, "mousedown");
    const bottomResizeStart$ = fromEvent<MouseEvent>(this.bottomHandle as HTMLElement, "mousedown");
    const bottomLeftResizeStart$ = fromEvent<MouseEvent>(this.bottomLeftHandle as HTMLElement, "mousedown");
    const bottomRightResizeStart$ = fromEvent<MouseEvent>(this.bottomRightHandle as HTMLElement, "mousedown");
    const topRightResizeStart$ = fromEvent<MouseEvent>(this.topRightHandle as HTMLElement, "mousedown");
    const topLeftResizeStart$ = fromEvent<MouseEvent>(this.topLeftHandle as HTMLElement, "mousedown");

    const resizeEnd$ = fromEvent<MouseEvent>(this.document, "mouseup");
    const resize$ = fromEvent<MouseEvent>(this.document, "mousemove").pipe(
      takeUntil(resizeEnd$)
    );

    let initialX: number,
      initialY: number,
      initialWidth: number,
      initialHeight: number,
      currentX = 0,
      currentY = 0;

    let dragSub: Subscription;
    let resizeSub: Subscription;

    const resizeRightStartSub = rightResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeRight(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeLeftStartSub = leftResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeLeft
      (initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeBottomStartSub = bottomResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeBottom(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeTopStartSub = topResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeTop(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeLeftTopStartSub = topLeftResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeLeftTop(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeRightTopStartSub = topRightResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeRightTop(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeLeftBottomStartSub = bottomLeftResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeLeftBottom(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeRightBottomStartSub = bottomRightResizeStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - currentX /*+ this.element.clientWidth*/;
      initialY = event.clientY - currentY;
      initialWidth = this.element.clientWidth;
      initialHeight = this.element.clientHeight;
      this.element.classList.add('resizing');
      this.resizingMethod = this.resizeRightBottom(initialX, initialY, initialWidth, initialHeight);
      resizeSub = resize$.subscribe((event: MouseEvent) => {
        this.resizingMethod(event);
      });
    });

    const resizeEndSub = resizeEnd$.subscribe((event: MouseEvent) => {
      initialX = currentX;
      initialY = currentY;

      this.element.classList.remove('resizing');
      if (resizeSub) {
        resizeSub.unsubscribe();
      }
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((s) => s?.unsubscribe());
  }

  resizeRight(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
      return (event: MouseEvent)=>{
        let currentX = event.clientX - initialX;
        this.element.style.width = currentX + initialWidth + "px";
      };
  }

  resizeLeft(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentX = event.clientX - initialX;
      if(event.clientX < initialX + initialWidth - 100){
        this.element.style.left = event.clientX + 'px';
        this.element.style.width = initialWidth - (event.clientX - initialX) + "px";
      }
    };
  }

  resizeBottom(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentY = event.clientY - initialY;
      this.element.style.height = currentY + initialHeight + "px";
    };
  }

  resizeTop(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      //let currentX = event.clientX - initialX;
      if(event.clientY < initialY + initialHeight - 100){
        this.element.style.top = event.clientY + 'px';
        this.element.style.height = initialHeight - (event.clientY - initialY) + "px";
      }
    };
  }

  resizeLeftTop(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentX = event.clientX - initialX;
      if(event.clientX < initialX + initialWidth - 100){
        this.element.style.left = event.clientX + 'px';
        this.element.style.width = initialWidth - (event.clientX - initialX) + "px";
      }
      if(event.clientY < initialY + initialHeight - 100){
        this.element.style.top = event.clientY + 'px';
        this.element.style.height = initialHeight - (event.clientY - initialY) + "px";
      }
    };
  }

  resizeRightTop(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentX = event.clientX - initialX;
      this.element.style.width = currentX + initialWidth + "px";
      if(event.clientY < initialY + initialHeight - 100){
        this.element.style.top = event.clientY + 'px';
        this.element.style.height = initialHeight - (event.clientY - initialY) + "px";
      }
    };
  }

  resizeLeftBottom(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentX = event.clientX - initialX;
      let currentY = event.clientY - initialY;
      this.element.style.height = currentY + initialHeight + "px";
      if(event.clientX < initialX + initialWidth - 100){
        this.element.style.left = event.clientX + 'px';
        this.element.style.width = initialWidth - (event.clientX - initialX) + "px";
      }
    };
  }

  resizeRightBottom(initialX: number, initialY: number, initialWidth: number, initialHeight: number) {
    return (event: MouseEvent)=>{
      let currentX = event.clientX - initialX;
      this.element.style.width = currentX + initialWidth + "px";
      let currentY = event.clientY - initialY;
      this.element.style.height = currentY + initialHeight + "px";
    };
  }
}
