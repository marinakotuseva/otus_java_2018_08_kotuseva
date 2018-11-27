package ru.otus;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class ClassFields {
    public static LinkedHashMap<String, Object> getClassFields(Class clazz){
        LinkedHashMap fields = new LinkedHashMap();
        int i = 0;
        for (Field f : clazz.getDeclaredFields()) {
            String fName = f.getName();
            Class<?> fType = f.getType();
            fields.put(fName, fType);
        }
        return fields;
    }
}
