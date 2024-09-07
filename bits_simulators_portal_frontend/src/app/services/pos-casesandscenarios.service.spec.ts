import { TestBed } from '@angular/core/testing';

import { PosCasesandscenariosService } from './pos-casesandscenarios.service';

describe('PosCasesandscenariosService', () => {
  let service: PosCasesandscenariosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PosCasesandscenariosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
