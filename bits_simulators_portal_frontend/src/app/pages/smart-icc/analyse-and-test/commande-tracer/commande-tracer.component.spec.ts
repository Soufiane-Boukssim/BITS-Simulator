import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandeTracerComponent } from './commande-tracer.component';

describe('CommandeTracerComponent', () => {
  let component: CommandeTracerComponent;
  let fixture: ComponentFixture<CommandeTracerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommandeTracerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommandeTracerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
