import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesManagementComponent } from './cases-management.component';

describe('CasesManagementComponent', () => {
  let component: CasesManagementComponent;
  let fixture: ComponentFixture<CasesManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasesManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CasesManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
