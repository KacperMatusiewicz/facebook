package ripoff.facebook.notification.preference.repository;

import ripoff.facebook.notification.commons.NotificationMethod;

public interface NotificationPreferenceEntry {

    String getDestination();

    NotificationMethod getNotificationMethod();
}
