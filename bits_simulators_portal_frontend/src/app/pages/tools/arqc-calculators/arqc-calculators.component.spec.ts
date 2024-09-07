import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArqcCalculatorsComponent } from './arqc-calculators.component';

describe('ArqcCalculatorsComponent', () => {
  let component: ArqcCalculatorsComponent;
  let fixture: ComponentFixture<ArqcCalculatorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArqcCalculatorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArqcCalculatorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
