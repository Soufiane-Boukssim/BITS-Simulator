import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionConfigurationComponent } from './transaction-configuration.component';

describe('TransactionConfigurationComponent', () => {
  let component: TransactionConfigurationComponent;
  let fixture: ComponentFixture<TransactionConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionConfigurationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransactionConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
