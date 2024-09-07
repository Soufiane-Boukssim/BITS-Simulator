import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParameteroptionsComponent } from './parameteroptions.component';

describe('ParameteroptionsComponent', () => {
  let component: ParameteroptionsComponent;
  let fixture: ComponentFixture<ParameteroptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParameteroptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParameteroptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
