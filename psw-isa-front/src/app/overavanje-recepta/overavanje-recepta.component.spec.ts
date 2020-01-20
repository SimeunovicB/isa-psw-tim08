import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OveravanjeReceptaComponent } from './overavanje-recepta.component';

describe('OveravanjeReceptaComponent', () => {
  let component: OveravanjeReceptaComponent;
  let fixture: ComponentFixture<OveravanjeReceptaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OveravanjeReceptaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OveravanjeReceptaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
