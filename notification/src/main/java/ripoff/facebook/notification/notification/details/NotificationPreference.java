package ripoff.facebook.notification.notification.details;

@FunctionalInterface
public interface NotificationPreference {
    NotificationPreferenceEntryCollection getPreferences();
}
