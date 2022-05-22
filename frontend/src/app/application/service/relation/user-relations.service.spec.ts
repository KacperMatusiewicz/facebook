import { TestBed } from '@angular/core/testing';

import { UserRelationsService } from './user-relations.service';

describe('UserRelationsService', () => {
  let service: UserRelationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRelationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
