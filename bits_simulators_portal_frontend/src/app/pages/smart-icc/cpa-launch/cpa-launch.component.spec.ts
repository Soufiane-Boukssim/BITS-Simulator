import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CpaLaunchComponent } from './cpa-launch.component';

describe('CpaLaunchComponent', () => {
  let component: CpaLaunchComponent;
  let fixture: ComponentFixture<CpaLaunchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CpaLaunchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CpaLaunchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
