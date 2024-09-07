import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvMiscComponent } from './env-misc.component';

describe('EnvMiscComponent', () => {
  let component: EnvMiscComponent;
  let fixture: ComponentFixture<EnvMiscComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnvMiscComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnvMiscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
