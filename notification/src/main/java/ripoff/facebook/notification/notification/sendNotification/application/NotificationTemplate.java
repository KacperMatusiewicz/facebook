package ripoff.facebook.notification.notification.sendNotification.application;

import ripoff.facebook.notification.notification.sendNotification.application.details.NotificationParameters;
import ripoff.facebook.notification.notification.sendNotification.application.details.NotificationPreference;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.notificationChannel.NotificationFactory;
import ripoff.facebook.notification.preference.repository.NotificationPreferenceEntry;
import ripoff.facebook.notification.utils.Collection;
import ripoff.facebook.notification.utils.Iterator;

public abstract class NotificationTemplate {

    public final void executeNotification(NotificationDTO notificationInformation) {
        NotificationPreference preference = retrieveNotificationPreferences(notificationInformation.getUserId());
        log(notificationInformation);
        notifyUser(notificationInformation.getNotificationParameters(), preference);
    }

    public abstract NotificationPreference retrieveNotificationPreferences(Long userId);

    public abstract void log(NotificationDTO information);

    public void notifyUser(NotificationParameters parameters, NotificationPreference preference) {
        NotificationFactory factory = new NotificationFactory();
        Collection<NotificationPreferenceEntry> collection = preference.getPreferences();
        Iterator<NotificationPreferenceEntry> iterator = collection.createIterator();
        NotificationPreferenceEntry notificationPreferenceEntry;
        for(notificationPreferenceEntry = iterator.getCurrent(); iterator.hasNext(); notificationPreferenceEntry = iterator.getNext()) {
            factory
                    .createNotification(
                            notificationPreferenceEntry.getNotificationMethod(),
                            notificationPreferenceEntry.getDestination()
                    )
                    .notify(parameters);
        }
    }
}
