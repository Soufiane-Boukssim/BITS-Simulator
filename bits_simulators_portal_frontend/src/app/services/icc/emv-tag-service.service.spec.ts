import { TestBed } from '@angular/core/testing';

import { EmvTagServiceService } from './emv-tag-service.service';

describe('EmvTagServiceService', () => {
  let service: EmvTagServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmvTagServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
