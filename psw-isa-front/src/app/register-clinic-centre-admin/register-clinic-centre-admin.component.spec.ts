import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterClinicCentreAdminComponent } from './register-clinic-centre-admin.component';

describe('RegisterClinicCentreAdminComponent', () => {
  let component: RegisterClinicCentreAdminComponent;
  let fixture: ComponentFixture<RegisterClinicCentreAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterClinicCentreAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterClinicCentreAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
