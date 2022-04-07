package ripoff.facebook.notification.utils;

public interface Iterator<T> {
    T getNext();
    boolean hasNext();
    T getCurrent();
}