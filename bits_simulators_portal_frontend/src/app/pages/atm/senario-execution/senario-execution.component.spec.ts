import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SenarioExecutionComponent } from './senario-execution.component';

describe('SenarioExecutionComponent', () => {
  let component: SenarioExecutionComponent;
  let fixture: ComponentFixture<SenarioExecutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SenarioExecutionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SenarioExecutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
