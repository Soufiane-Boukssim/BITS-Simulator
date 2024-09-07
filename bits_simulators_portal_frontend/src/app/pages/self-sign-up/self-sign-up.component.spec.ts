import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfSignUpComponent } from './self-sign-up.component';

describe('SelfSignUpComponent', () => {
  let component: SelfSignUpComponent;
  let fixture: ComponentFixture<SelfSignUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelfSignUpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelfSignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
