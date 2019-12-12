import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAppointTypesComponent } from './manage-appoint-types.component';

describe('ManageAppointTypesComponent', () => {
  let component: ManageAppointTypesComponent;
  let fixture: ComponentFixture<ManageAppointTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAppointTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAppointTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
