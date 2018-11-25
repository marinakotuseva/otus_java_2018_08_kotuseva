package ru.otus;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person pers = new Person();
        pers.name = "Casey";
        pers.surname = "Smith";
        pers.age = 27;
        pers.hobby = "Saving the world";
        pers.bool = true;
        pers.intNum = 3;

        pers.address = new HashMap<>();
        pers.address.put("street", "BakerStreet");
        pers.address.put("house", "221b");

        int[] arr = {2, 3};
        pers.arr = arr;
        pers.coll = new ArrayList();
        pers.coll.add(0, "1");
        pers.coll.add(0, "2");

        JSonConverter conv = new JSonConverter();

        String res = conv.objectToJson(pers);
        System.out.println("=======");
        System.out.println("Custom: " + res);

        Gson gson = new Gson();
        System.out.println("Gson:   " + gson.toJson(pers));

    }




}