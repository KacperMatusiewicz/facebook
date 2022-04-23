import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WinButtonComponent } from './win-button.component';

describe('WinButtonComponent', () => {
  let component: WinButtonComponent;
  let fixture: ComponentFixture<WinButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WinButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WinButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
