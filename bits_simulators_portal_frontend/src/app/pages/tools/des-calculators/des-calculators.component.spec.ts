import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesCalculatorsComponent } from './des-calculators.component';

describe('DesCalculatorsComponent', () => {
  let component: DesCalculatorsComponent;
  let fixture: ComponentFixture<DesCalculatorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DesCalculatorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DesCalculatorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
