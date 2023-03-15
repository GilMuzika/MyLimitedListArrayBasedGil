package org.example;

import java.util.Arrays;

public class MyLimitedList<T> implements IMyLimitedList<T> {

    private T[] _m_items;
    private int _m_max_items;

    public MyLimitedList(int m_max_items) {
        _m_max_items = m_max_items;
        _m_items = (T[]) new Object[_m_max_items];
    }

    @Override
    public void addItem(T item) {
        if(countRealItems() < _m_max_items) {
            _m_items[findFirstEmpty()] = item;
        } else {
            shiftForwardFromIndex(0); //the last item will be overwritten by the previous (before the last) item so it will be lost
            _m_items[0] = item;
        }
    }

    @Override
    public T getItem(int itemIndex) {
        if(itemIndex > _m_max_items - 1)
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", itemIndex, _m_max_items));
        return _m_items[itemIndex];
    }

    @Override
    public void removeItem(int itemIndex) {
        if(itemIndex > _m_max_items - 1)
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", itemIndex, _m_max_items));

        shiftBackwardToIndex(itemIndex); //after the shift the last item will be duplicated also to the one before the last position
        _m_items[_m_max_items - 1] = null;
    }

    @Override
    public int maxSize() {
        return _m_max_items;
    }

    @Override
    public int count() {
        return countRealItems();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(_m_items).forEach((x)-> {
            if(x != null)
                sb.append(x.toString() + "\n");
        });
        return sb.toString();
    }

    public void enlargeBy(int factor) {
        _m_max_items += factor;
        T[] tempLargeArr = (T[]) new Object[_m_max_items];
        for(int i = 0; i < _m_items.length; i++) {
            tempLargeArr[i] = _m_items[i];
        }
        _m_items = tempLargeArr;
    }

    private int findFirstEmpty() {
        int count = 0;
        for(T one : _m_items) {
            if(one == null)
                break;
            count++;
        }
        return count;
    }

    private void shiftForwardFromIndex(int index) {
        for(int i = _m_max_items - 1; i > index; i--) {
            _m_items[i] = _m_items[i - 1];
        }
    }
    private void shiftBackwardToIndex(int index) {
        for(int i = index; i < _m_max_items - 1; i++) {
            _m_items[i] = _m_items[i + 1];
        }
    }

    private int countRealItems(){
        int count  = 0;
        for(T one : _m_items) {
            if(one != null)
                count++;
        }
        return count;
    }

}
