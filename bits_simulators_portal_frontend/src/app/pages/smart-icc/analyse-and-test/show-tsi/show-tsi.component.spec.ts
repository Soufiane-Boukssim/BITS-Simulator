import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTsiComponent } from './show-tsi.component';

describe('ShowTsiComponent', () => {
  let component: ShowTsiComponent;
  let fixture: ComponentFixture<ShowTsiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowTsiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowTsiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
