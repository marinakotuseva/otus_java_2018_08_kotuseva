package ru.otus;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassMetaDataHolder {
    static Map<Class, LinkedHashMap<String, Class>> cachedClassFields;

    public static LinkedHashMap<String, Class> getClassFields(Class clazz){
        LinkedHashMap<String, Class>  fields = new LinkedHashMap();
        if(cachedClassFields == null){
            for (Field f : clazz.getDeclaredFields()) {
                String fName = f.getName();
                Class<?> fType = f.getType();
                fields.put(fName, fType);
            }
            cachedClassFields = new LinkedHashMap<Class, LinkedHashMap<String, Class>>();
            cachedClassFields.put(clazz, fields);
        } else {
            fields = cachedClassFields.get(clazz);
        }
        return fields;
    }
}
