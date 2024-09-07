import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralOptionsAdminComponent } from './general-options-admin.component';

describe('GeneralOptionsAdminComponent', () => {
  let component: GeneralOptionsAdminComponent;
  let fixture: ComponentFixture<GeneralOptionsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeneralOptionsAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GeneralOptionsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
