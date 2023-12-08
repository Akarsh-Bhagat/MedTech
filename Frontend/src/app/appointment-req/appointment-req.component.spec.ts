import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentReqComponent } from './appointment-req.component';

describe('AppointmentReqComponent', () => {
  let component: AppointmentReqComponent;
  let fixture: ComponentFixture<AppointmentReqComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppointmentReqComponent]
    });
    fixture = TestBed.createComponent(AppointmentReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
