package ru.otus;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIterator implements ListIterator {
    private int index = -1;
    private MyArrayList myArrayList;

    public MyListIterator(MyArrayList myArrayList){
        this.myArrayList = myArrayList;
    }

    @Override
    public boolean hasNext() {
        if (index < myArrayList.size()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        index++;
        return myArrayList.get(index);
    }

    @Override
    public boolean hasPrevious() {
        if (index >= 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MyArrayList previous() {
        index--;
        return (MyArrayList) myArrayList.get(index);
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        myArrayList.remove(index);

    }

    @Override
    public void set(Object o) {
        myArrayList.set(index, o);
    }

    @Override
    public void add(Object o) {
        myArrayList.add(o);
    }

}
