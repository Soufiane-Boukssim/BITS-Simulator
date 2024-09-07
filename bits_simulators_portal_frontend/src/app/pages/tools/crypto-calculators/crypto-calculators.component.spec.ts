import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoCalculatorsComponent } from './crypto-calculators.component';

describe('CryptoCalculatorsComponent', () => {
  let component: CryptoCalculatorsComponent;
  let fixture: ComponentFixture<CryptoCalculatorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CryptoCalculatorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CryptoCalculatorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
