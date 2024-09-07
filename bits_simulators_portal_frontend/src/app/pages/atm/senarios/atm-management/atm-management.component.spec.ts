import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtmManagementComponent } from './atm-management.component';

describe('AtmManagementComponent', () => {
  let component: AtmManagementComponent;
  let fixture: ComponentFixture<AtmManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtmManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtmManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
