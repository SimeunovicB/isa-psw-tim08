import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchClinicsComponent } from './search-clinics.component';

describe('SearchClinicsComponent', () => {
  let component: SearchClinicsComponent;
  let fixture: ComponentFixture<SearchClinicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchClinicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchClinicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
