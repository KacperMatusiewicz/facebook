package ripoff.facebook.notification.notification.sendNotification.application.details;

import ripoff.facebook.notification.preference.repository.NotificationPreferenceEntry;
import ripoff.facebook.notification.utils.Iterator;

public class NotificationPreferenceEntryIterator implements Iterator<NotificationPreferenceEntry> {
    private NotificationPreferenceEntryCollection collection;
    private int currentIndex;

    public NotificationPreferenceEntryIterator(NotificationPreferenceEntryCollection collection) {
        this.collection = collection;
        this.currentIndex = 0;
    }

    @Override
    public NotificationPreferenceEntry getNext() {
        return collection.getPreferences().get(++currentIndex);
    }

    @Override
    public boolean hasNext() {
        return (currentIndex + 1) < collection.getPreferences().size();
    }

    @Override
    public NotificationPreferenceEntry getCurrent() {
        return collection.getPreferences().get(currentIndex);
    }

}
