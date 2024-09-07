import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosCasesManagementComponent } from './pos-cases-management.component';

describe('PosCasesManagementComponent', () => {
  let component: PosCasesManagementComponent;
  let fixture: ComponentFixture<PosCasesManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosCasesManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosCasesManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
