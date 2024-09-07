import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConfigurationComponent } from './new-configuration.component';

describe('NewConfigurationComponent', () => {
  let component: NewConfigurationComponent;
  let fixture: ComponentFixture<NewConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewConfigurationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
