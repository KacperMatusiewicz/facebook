import { TestBed } from '@angular/core/testing';

import { UserRelationsStoreService } from './user-relations-store.service';

describe('UserRelationsStoreService', () => {
  let service: UserRelationsStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRelationsStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
