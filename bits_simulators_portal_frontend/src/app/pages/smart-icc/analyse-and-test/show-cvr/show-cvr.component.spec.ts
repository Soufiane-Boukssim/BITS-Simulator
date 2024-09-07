import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCvrComponent } from './show-cvr.component';

describe('ShowCvrComponent', () => {
  let component: ShowCvrComponent;
  let fixture: ComponentFixture<ShowCvrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowCvrComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowCvrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
