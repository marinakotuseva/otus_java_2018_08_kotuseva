package ru.otus;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JSonConverter {
    private static String toJson(Object object) {
        if (object == null){
            return "null";
        } else {
            Class objectClass = object.getClass();
            StringBuilder res = new StringBuilder();
            if (objectClass.isPrimitive()) {
                if (object instanceof Integer || object instanceof Boolean) {
                    res.append(object.toString());
                } else {
                    res.append("\"" + object.toString() + "\"");
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
                res.append(s);
            } else if (Collection.class.isAssignableFrom(objectClass)) {
                Collection objectToCollection = (Collection) object;
                String s ="[";
                for (Object o :
                        objectToCollection) {
                    s+=toJson(o);
                    s+=",";
                }
                s = s.substring(0, s.length() - 1);
                s+="]";
                res.append(s);
            } else if (Map.class.isAssignableFrom(objectClass)) {
                Map<Object, Object> objectToMap = (Map) object;
                res.append("{");
                for (var entry :
                        objectToMap.entrySet()) {
                    res.append("\"" + entry.getKey() + "\"");
                    res.append(":");
                    res.append(toJson(entry.getValue()));
                    res.append(",");
                }
                res.deleteCharAt(res.lastIndexOf(","));
                res.append("}");

            } else {
                if (object instanceof Integer || object instanceof Boolean || object instanceof List) {
                    res.append(object.toString());
                } else {
                    res.append("\"" + object.toString() + "\"");
                }
            }
            return res.toString();
        }
    }

    public static String objectToJson(Object object){
        if (object == null){
            return null;
        } else {

            Class c = object.getClass();
            StringBuilder res = new StringBuilder();

            if (c.isArray()) {
                res.append(toJson(object));
            } else if (object instanceof List || object instanceof Map) {
                res.append(toJson(object));
            } else {
                res.append("{");
                for (Field f : object.getClass().getDeclaredFields()) {
                    String fName = f.getName();

                    try {
                        f.setAccessible(true);
                        Object fValue = f.get(object);
                        res.append("\"" + fName + "\"");
                        res.append(":");
                        res.append(toJson(fValue));
                    } catch (Exception e) {
                        res.append("\"" + "error getting value" + "\"");
                        e.printStackTrace();
                    }
                    res.append(",");
                }
                res.deleteCharAt(res.lastIndexOf(","));
                res.append("}");
            }
            return res.toString();
        }
    }
}
