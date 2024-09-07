import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestingResultComponent } from './testing-result.component';

describe('TestingResultComponent', () => {
  let component: TestingResultComponent;
  let fixture: ComponentFixture<TestingResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestingResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestingResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
