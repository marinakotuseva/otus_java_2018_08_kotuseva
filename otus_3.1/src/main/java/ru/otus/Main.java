package ru.otus;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args) {

        MyArrayList<String> s = new MyArrayList<String>();
        MyArrayList<String> s2 = new MyArrayList<String>();
        s.add("Hello");
        s.add(",");
        s.add(" ");
        s.add("World");
        System.out.println(s.toString());
        System.out.println("Initial s: "+s.toString());

        // Add All
        String[] s3 = new String[]{"!"};
        Collections.addAll(s, s3);
        System.out.println("After AddAll s: "+s.toString());

        // Sort


    }


}
