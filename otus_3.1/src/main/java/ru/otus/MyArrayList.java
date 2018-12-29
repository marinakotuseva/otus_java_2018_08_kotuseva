package ru.otus;

import javax.lang.model.type.NullType;
import java.util.*;


class MyArrayList <T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int elCount;
    private Object[] data;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException("Array size cannot be negative");
        }

        data = new Object[initialCapacity];
    }

    //static <T> void sort(List<T> list, Comparator<? super T> c) {
    //    Arrays.sort(list, (Comparator<? super Object>) c);
    //}

    public int size() {
        int count = 0;
        for (Integer i  = 0; i < data.length; i++) {
            ++count;
        }
        return count;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported");
    }
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported");
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean add(T t){
        if (data.length <= elCount){
            Object[] dataNew = new Object[data.length*2];
            for (int i = 0; i < data.length; i++) {
                dataNew[i] = data[i];
            }
            data = dataNew;
            dataNew = null;
        }
        if (t != null) {
            data[elCount] = t;
            ++elCount;
        }
        return true;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean addAll(int index, Collection<? extends T> c) {
            Object[] arrayToAdd = c.toArray();
            int arrayToAddLength = arrayToAdd.length;
            if (arrayToAddLength == 0)
                return false;
            //Object[] elementData;
            final int s;
            System.arraycopy(arrayToAdd, 0, this, this.size(), arrayToAddLength);
            return true;
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    public void clear() {

    }

    public T get(int index) {
        return (T) data[index];
    }

    public T set(int index, T element) {
        data[index] = element;
        return get(index);
    }

    public void add(int index, T element) {

    }

    public T remove(int index) {
        throw new UnsupportedOperationException("Not supported");
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    public ListIterator<T> listIterator() {
        ListIterator iterator = new MyListIterator(this);
        return iterator;
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported");
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported");
    }

    public String toString() {
        String s = "";
        for (int i  = 0; i < data.length; i++) {
            if (data[i] != null){
                s = s + data[i];
            }
        }
        return s;
    }
}
