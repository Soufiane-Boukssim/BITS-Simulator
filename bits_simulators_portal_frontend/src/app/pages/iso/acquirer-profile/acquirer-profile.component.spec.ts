import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcquirerProfileComponent } from './acquirer-profile.component';

describe('AcquirerProfileComponent', () => {
  let component: AcquirerProfileComponent;
  let fixture: ComponentFixture<AcquirerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcquirerProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcquirerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
