package ripoff.facebook.notification.notification.details;

import ripoff.facebook.notification.notification.details.NotificationPreferenceEntryCollection;

@FunctionalInterface
public interface NotificationPreference {
    NotificationPreferenceEntryCollection getPreferences();
}
