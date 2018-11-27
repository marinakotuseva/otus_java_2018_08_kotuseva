package ru.otus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClassMetaDataHolder {
    static Map<Class, LinkedHashMap<String, Class>> cachedClassFields;
    static Map<Class, String> cachedInsertIntoQuery;
    static Map<Class, String> cachedSelectByIdQuery;
    static Map<Class, String> cachedTableName;


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

    public static String getInsertIntoTableQuery(Class clazz){
        if(cachedInsertIntoQuery == null) {
            String tName = getTableNameForClass(clazz);

            StringBuilder insertIntoTableQuery = new StringBuilder();
            StringBuilder insertIntoTableValues = new StringBuilder();

            insertIntoTableQuery.append("insert into " + tName);
            insertIntoTableQuery.append("(");
            insertIntoTableValues.append(" values(");

            LinkedHashMap<String, Class> fields = ClassMetaDataHolder.getClassFields(clazz);
            for (Map.Entry entry: fields.entrySet()
            ) {
                Object fName = entry.getKey();
                insertIntoTableQuery.append(fName + ",");
                insertIntoTableValues.append("?,");
            }


            insertIntoTableQuery.deleteCharAt(insertIntoTableQuery.lastIndexOf(","));
            insertIntoTableQuery.append(")");

            insertIntoTableValues.deleteCharAt(insertIntoTableValues.lastIndexOf(","));
            insertIntoTableValues.append(")");

            insertIntoTableQuery.append(insertIntoTableValues.toString());
            cachedInsertIntoQuery = new LinkedHashMap<Class, String>();
            cachedInsertIntoQuery.put(clazz, insertIntoTableQuery.toString());
        }

        return cachedInsertIntoQuery.get(clazz);
    }

    public static String getSelectByIdQuery(Class clazz){
        if (cachedSelectByIdQuery == null) {
            String tName = getTableNameForClass(clazz);
            StringBuilder selectByID = new StringBuilder();
            selectByID.append("select ");

            LinkedHashMap<String, Class> fields = getClassFields(clazz);
            for (Map.Entry entry : fields.entrySet()
            ) {
                Object fName = entry.getKey();
                selectByID.append(fName + ",");
            }
            selectByID.deleteCharAt(selectByID.lastIndexOf(","));
            selectByID.append(" from " + tName);
            selectByID.append(" where id = ?");
            cachedSelectByIdQuery = new LinkedHashMap<Class, String>();
            cachedSelectByIdQuery.put(clazz, selectByID.toString());
        }
        return cachedSelectByIdQuery.get(clazz);
    }

    public static String getTableNameForClass(Class clazz){
        if (cachedTableName == null){
            cachedTableName = new LinkedHashMap<Class, String>();
            cachedTableName.put(clazz, clazz.getName().replace(clazz.getPackageName()+".", ""));
        }
        return cachedTableName.get(clazz);
    }

    public static List<Class> getConstructorParams(Class clazz){

        List<Class> constrParamsList = new ArrayList<Class>();
        LinkedHashMap<String, Class> fields = ClassMetaDataHolder.getClassFields(clazz);
        for (Map.Entry entry: fields.entrySet()
        ) {
            Object fName = entry.getKey();
            Field f = null;
            try {
                f = clazz.getDeclaredField((String)fName);
            } catch (NoSuchFieldException e) {
                System.out.println("No field " + fName);
                e.printStackTrace();
            }
            Class fClass = f.getType();
            constrParamsList.add(fClass);
        }
        return constrParamsList;
    }

}
