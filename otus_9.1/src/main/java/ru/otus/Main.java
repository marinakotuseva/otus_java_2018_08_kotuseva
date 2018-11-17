package ru.otus;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Person pers = new Person();
        pers.name = "Casey";
        pers.surname = "Smith";
        pers.age = 27;
        pers.hobby = "Saving the world";
        String[][] ad = new String[2][2];
        ad[0] = new String[]{"street", "BakerStreet"};
        ad[1] = new String[]{"house", "221b"};
        pers.address = ad;
        pers.bool = true;

        String res = "[{";
        for (Field f:
                pers.getClass().getDeclaredFields()) {
            String fType = f.getType().getName();
            String fName = f.getName();
            System.out.println(fType);
            switch (fType) {
                case "java.lang.String":
                case "java.lang.Integer":
                case "boolean":
                case "byte":
                case "short":
                case "char":
                case "int":
                case "long":
                case "float":
                case "double":
                    try {
                        Object fValue = f.get(pers);
                        res += "\"" + fName + "\"" + ":" + "\"" + fValue+ "\"" + ",\n" ;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "[[Ljava.lang.String;":
                    try {
                        String[][] fValue = (String[][]) f.get(pers);
                        res += "\"" + fName + "\"" + ":\n[{";
                        for (String[] arr:
                                fValue) {
                            res+="\"" + arr[0] + "\":" + "\"" + arr[1] + "\"" + ",";
                        }
                        res = res.substring(0, res.length() - 1);
                        res+="}]\n";
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
        res+="\n}]";
        System.out.println(res);
    }
}
