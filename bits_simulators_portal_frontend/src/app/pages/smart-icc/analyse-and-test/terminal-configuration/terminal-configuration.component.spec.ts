import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerminalConfigurationComponent } from './terminal-configuration.component';

describe('TerminalConfigurationComponent', () => {
  let component: TerminalConfigurationComponent;
  let fixture: ComponentFixture<TerminalConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TerminalConfigurationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TerminalConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
