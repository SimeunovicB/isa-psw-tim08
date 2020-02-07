import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomingExaminationsComponent } from './incoming-examinations.component';

describe('IncomingExaminationsComponent', () => {
  let component: IncomingExaminationsComponent;
  let fixture: ComponentFixture<IncomingExaminationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomingExaminationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomingExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
