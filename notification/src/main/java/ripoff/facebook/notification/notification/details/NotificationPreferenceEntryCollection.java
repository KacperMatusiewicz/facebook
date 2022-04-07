package ripoff.facebook.notification.notification.details;

import ripoff.facebook.notification.preference.repository.NotificationPreferenceEntry;
import ripoff.facebook.notification.utils.Collection;
import ripoff.facebook.notification.utils.Iterator;

import java.util.List;

public class NotificationPreferenceEntryCollection implements Collection<NotificationPreferenceEntry> {

    List<NotificationPreferenceEntry> preferences;

    public NotificationPreferenceEntryCollection(List<NotificationPreferenceEntry> preferences) {
        this.preferences = preferences;
    }

    @Override
    public Iterator<NotificationPreferenceEntry> createIterator() {
        return new NotificationPreferenceEntryIterator(this);
    }

    public List<NotificationPreferenceEntry> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<NotificationPreferenceEntry> preferences) {
        this.preferences = preferences;
    }
}