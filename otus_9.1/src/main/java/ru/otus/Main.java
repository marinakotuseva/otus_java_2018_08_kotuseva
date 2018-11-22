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

        String res = objectToJson(pers);
        System.out.println("=======");
        System.out.println("Custom: " + res);

        Gson gson = new Gson();
        System.out.println("Gson:   " + gson.toJson(pers));

    }



    public static String toJson(Object object) {
        if (object == null){
            return "null";
        } else {
            Class objectClass = object.getClass();
            String res = "";
            if (objectClass.isPrimitive()) {
                if (object instanceof Integer || object instanceof Boolean) {
                    res = object.toString();
                } else {
                    res = "\"" + object.toString() + "\"";
                }
            } else if (objectClass.isArray()) {
                int length = Array.getLength(object);
                String s ="[";
                for (int i = 0; i < length; i++) {
                    Object el = Array.get(object, i);
                    s+=toJson(el);
                    s+=",";
                }
                s = s.substring(0, s.length() - 1);
                s+="]";
                res = s;
            } else if (objectClass.isAssignableFrom(Collection.class)) {
                Collection objectToCollection = (Collection) object;
                String s ="[";
                for (Object o :
                        objectToCollection) {
                    s+=toJson(o);
                    s+=",";
                }
                s = s.substring(0, s.length() - 1);
                s+="]";
                res = s;
            } else if (objectClass.isAssignableFrom(HashMap.class)) {
                Map<Object, Object> objectToMap = (Map) object;
                res = "{";
                for (var entry :
                        objectToMap.entrySet()) {
                    res += "\"" + entry.getKey() + "\"";
                    res += ":";
                    res += toJson(entry.getValue());
                    res += ",";
                }
                res = res.substring(0, res.length() - 1);
                res += "}";

            } else {
                if (object == null) {
                    res = "\"" + null + "\"";
                } else {
                    if (object instanceof Integer || object instanceof Boolean) {
                        res = object.toString();
                    } else {
                        res = "\"" + object.toString() + "\"";
                    }
                }
            }
            return res;
        }
    }

    public static String objectToJson(Object object){

        StringBuilder res = new StringBuilder();
        res.append("{");

        for (Field f: object.getClass().getDeclaredFields()) {
            String fName = f.getName();

            try {
                f.setAccessible(true);
                Object fValue = f.get(object);
                res.append("\"" + fName + "\"");
                res.append(":");
                res.append(toJson(fValue));
            } catch (Exception e){
                res.append("\"" + "error getting value" + "\"");
                e.printStackTrace();
            }
            res.append(",");
        }
        res.deleteCharAt(res.lastIndexOf(","));
        res.append("}");
        return res.toString();
    }
}