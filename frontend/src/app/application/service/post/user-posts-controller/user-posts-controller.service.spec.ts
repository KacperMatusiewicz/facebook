import { TestBed } from '@angular/core/testing';

import { UserPostsControllerService } from './user-posts-controller.service';

describe('UserPostsControllerService', () => {
  let service: UserPostsControllerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPostsControllerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
