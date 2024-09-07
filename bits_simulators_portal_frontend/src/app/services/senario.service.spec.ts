import { TestBed } from '@angular/core/testing';

import { SenarioService } from './senario.service';

describe('SenarioService', () => {
  let service: SenarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SenarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
