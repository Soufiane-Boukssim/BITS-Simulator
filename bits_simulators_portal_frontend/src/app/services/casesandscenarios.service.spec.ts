import { TestBed } from '@angular/core/testing';

import { CasesandscenariosService } from './casesandscenarios.service';

describe('CasesandscenariosService', () => {
  let service: CasesandscenariosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CasesandscenariosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
