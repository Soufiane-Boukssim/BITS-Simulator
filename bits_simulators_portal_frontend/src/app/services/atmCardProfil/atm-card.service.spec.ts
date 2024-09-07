import { TestBed } from '@angular/core/testing';

import { AtmCardService } from './atm-card.service';

describe('AtmCardService', () => {
  let service: AtmCardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtmCardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
