import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestingExecutionComponent } from './testing-execution.component';

describe('TestingExecutionComponent', () => {
  let component: TestingExecutionComponent;
  let fixture: ComponentFixture<TestingExecutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestingExecutionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestingExecutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
