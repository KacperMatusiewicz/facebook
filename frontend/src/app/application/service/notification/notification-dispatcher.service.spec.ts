import { TestBed } from '@angular/core/testing';

import { NotificationDispatcherService } from './notification-dispatcher.service';

describe('NotificationDispatcherService', () => {
  let service: NotificationDispatcherService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotificationDispatcherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
