import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosTestingExecutionComponent } from './pos-testing-execution.component';

describe('PosTestingExecutionComponent', () => {
  let component: PosTestingExecutionComponent;
  let fixture: ComponentFixture<PosTestingExecutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosTestingExecutionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosTestingExecutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
