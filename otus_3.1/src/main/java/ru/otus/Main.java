package ru.otus;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args) {

        MyArrayList<String> s = new MyArrayList<String>();
        s.add("Hello");
        s.add(",");
        s.add(" ");
        s.add("World");
        System.out.println(s.toString());


        MyArrayList<String> s2 = new MyArrayList<String>();
        java.util.Collections.copy(s, s2);
        System.out.println(s2.toString());

    }


}
