import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentReservationComponent } from './appointment-reservation.component';

describe('AppointmentReservationComponent', () => {
  let component: AppointmentReservationComponent;
  let fixture: ComponentFixture<AppointmentReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointmentReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
