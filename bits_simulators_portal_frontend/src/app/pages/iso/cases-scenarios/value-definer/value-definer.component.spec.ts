import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValueDefinerComponent } from './value-definer.component';

describe('ValueDefinerComponent', () => {
  let component: ValueDefinerComponent;
  let fixture: ComponentFixture<ValueDefinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValueDefinerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValueDefinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
