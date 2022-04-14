package ripoff.facebook.notification.notification.sendNotification.application.details;

@FunctionalInterface
public interface NotificationPreference {
    NotificationPreferenceEntryCollection getPreferences();
}
