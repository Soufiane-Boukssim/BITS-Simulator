import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuesConfigurationComponent } from './issues-configuration.component';

describe('IssuesConfigurationComponent', () => {
  let component: IssuesConfigurationComponent;
  let fixture: ComponentFixture<IssuesConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuesConfigurationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssuesConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
