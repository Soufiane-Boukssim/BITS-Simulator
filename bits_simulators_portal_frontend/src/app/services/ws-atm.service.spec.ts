import { TestBed } from '@angular/core/testing';

import { WsAtmService } from './ws-atm.service';

describe('WsAtmService', () => {
  let service: WsAtmService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WsAtmService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
