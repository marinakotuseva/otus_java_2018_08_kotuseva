package ru.otus;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args) {

        MyArrayList<String> s = new MyArrayList<String>();
        MyArrayList<String> s2 = new MyArrayList<String>();
        s.add("World");
        s.add(",");
        s.add(" ");
        s.add("Hello");
        System.out.println(s.toString());
        System.out.println("Initial s: "+s.toString());

        // Add All
        String[] s3 = new String[]{"!"};
        Collections.addAll(s, s3);
        System.out.println("After AddAll s: "+s.toString());

        // Sort
        Collections.sort(s);
        System.out.println("After sort s: "+s.toString());

        // Copy
        s2.add("Smith");
        Collections.copy(s, s2);
        System.out.println("After copy s: "+s.toString());


    }


}
