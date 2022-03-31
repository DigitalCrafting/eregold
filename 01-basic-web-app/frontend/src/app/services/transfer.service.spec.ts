import { TestBed } from '@angular/core/testing';

import { TransferService } from './transfer.service';
import {HttpClientModule} from "@angular/common/http";

describe('TransfersService', () => {
  let service: TransferService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
          HttpClientModule
      ]
    });
    service = TestBed.inject(TransferService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
