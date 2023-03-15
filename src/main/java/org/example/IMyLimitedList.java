package org.example;

public interface IMyLimitedList<T> {
    void addItem(T item);
    T getItem(int itemIndex);
    void removeItem(int itemIndex);
    int maxSize();
    int count();
}
