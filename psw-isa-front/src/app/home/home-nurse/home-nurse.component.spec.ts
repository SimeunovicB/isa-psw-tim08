import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeNurseComponent } from './home-nurse.component';

describe('HomeNurseComponent', () => {
  let component: HomeNurseComponent;
  let fixture: ComponentFixture<HomeNurseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeNurseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeNurseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
