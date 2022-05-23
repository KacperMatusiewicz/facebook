import { TestBed } from '@angular/core/testing';

import { FeedInfoStoreService } from './feed-info-store.service';

describe('FeedInfoStoreService', () => {
  let service: FeedInfoStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeedInfoStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
