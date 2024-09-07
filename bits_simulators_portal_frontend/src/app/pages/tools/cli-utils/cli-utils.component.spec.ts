import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CliUtilsComponent } from './cli-utils.component';

describe('CliUtilsComponent', () => {
  let component: CliUtilsComponent;
  let fixture: ComponentFixture<CliUtilsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CliUtilsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CliUtilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
