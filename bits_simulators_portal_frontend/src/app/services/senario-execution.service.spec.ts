import { TestBed } from '@angular/core/testing';

import { SenarioExecutionService } from './senario-execution.service';

describe('SenarioExecutionService', () => {
  let service: SenarioExecutionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SenarioExecutionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
