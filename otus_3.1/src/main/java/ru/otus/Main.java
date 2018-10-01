package ru.otus;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        MyArrayList<String> s = new MyArrayList<String>();
        s.add("Hello");
        s.add(",");
        s.add(" ");
        s.add("World");
        //System.out.println(s.size());
        System.out.println(s.toString());
        //s.sort();
        //MyArrayList.sort();


        MyArrayList<String> s2 = new MyArrayList<String>();
        MyArrayList.copy(s, s2);
        System.out.println(s2.toString());

    }


}
