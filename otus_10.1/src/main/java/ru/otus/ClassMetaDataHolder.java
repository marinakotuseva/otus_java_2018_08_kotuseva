package ru.otus;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassMetaDataHolder {
    static Map<Class, LinkedHashMap<String, Class>> cachedClassFields;
    static String cachedInsertIntoQuery;
    static String cachedSelectByIdQuery;


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
            cachedInsertIntoQuery = insertIntoTableQuery.toString();
        }

        return cachedInsertIntoQuery;
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
            cachedSelectByIdQuery = selectByID.toString();
        }
        return cachedSelectByIdQuery;
    }

    public static String getTableNameForClass(Class clazz){
        return clazz.getName().replace(clazz.getPackageName()+".", "");
    }

}
