import { TestBed } from '@angular/core/testing';

import { UserRelationsControllerService } from './user-relations-controller.service';

describe('UserRelationsControllerService', () => {
  let service: UserRelationsControllerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRelationsControllerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
