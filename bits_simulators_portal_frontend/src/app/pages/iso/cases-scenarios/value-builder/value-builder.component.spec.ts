import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValueBuilderComponent } from './value-builder.component';

describe('ValueBuilderComponent', () => {
  let component: ValueBuilderComponent;
  let fixture: ComponentFixture<ValueBuilderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValueBuilderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValueBuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
