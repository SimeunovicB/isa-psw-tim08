import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredefAppointmentsComponent } from './predef-appointments.component';

describe('PredefAppointmentsComponent', () => {
  let component: PredefAppointmentsComponent;
  let fixture: ComponentFixture<PredefAppointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredefAppointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PredefAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
