import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutionScriptComponent } from './execution-script.component';

describe('ExecutionScriptComponent', () => {
  let component: ExecutionScriptComponent;
  let fixture: ComponentFixture<ExecutionScriptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExecutionScriptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutionScriptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
