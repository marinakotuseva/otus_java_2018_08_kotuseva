package ru.otus;

import javax.lang.model.type.NullType;
import java.util.*;


class MyArrayList <T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 20;
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
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t){
        //if (t == null) {throw new NullPointerException();}
        if (t != null) {
            data[elCount] = t;
            ++elCount;
        }
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
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
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public T get(int index) {
        return null;
    }

    public T set(int index, T element) {
        return null;
    }

    public void add(int index, T element) {

    }

    public T remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
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
