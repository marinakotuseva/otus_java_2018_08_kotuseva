package ru.otus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClassMetaDataHolder {
    private Map<Class, LinkedHashMap<String, Field>> cachedClassFields;
    private Map<Class, String> cachedInsertIntoQuery;
    private Map<Class, String> cachedSelectByIdQuery;
    private Map<Class, String> cachedTableName;


    public LinkedHashMap<String, Field> getClassFields(Class clazz){

        boolean addClassFields = false;
        if(cachedClassFields == null){
            cachedClassFields = new LinkedHashMap<>();
            addClassFields = true;
        } else {
            addClassFields = !cachedClassFields.containsKey(clazz);
        }
        if (addClassFields) {
            LinkedHashMap<String, Field>  fields = new LinkedHashMap();
            for (Field f : clazz.getDeclaredFields()) {
                String fName = f.getName();
                fields.put(fName, f);
            }
            cachedClassFields.put(clazz, fields);
        }
        return cachedClassFields.get(clazz);
    }

    public String getInsertIntoTableQuery(Class clazz){
        boolean addQuery= false;
        if(cachedInsertIntoQuery == null) {
            cachedInsertIntoQuery = new LinkedHashMap<>();
            addQuery = true;
        } else {
            addQuery = !cachedInsertIntoQuery.containsKey(clazz);
        }
        if (addQuery) {
            StringBuilder insertIntoTableQuery = new StringBuilder();
            StringBuilder insertIntoTableValues = new StringBuilder();

            String tName = getTableNameForClass(clazz);
            insertIntoTableQuery.append("insert into " + tName);
            insertIntoTableQuery.append("(");
            insertIntoTableValues.append(" values(");

            LinkedHashMap<String, Field> fields = this.getClassFields(clazz);
            for (Map.Entry entry: fields.entrySet()) {
                Object fName = entry.getKey();
                insertIntoTableQuery.append(fName + ",");
                insertIntoTableValues.append("?,");
            }

            insertIntoTableQuery.deleteCharAt(insertIntoTableQuery.lastIndexOf(","));
            insertIntoTableQuery.append(")");

            insertIntoTableValues.deleteCharAt(insertIntoTableValues.lastIndexOf(","));
            insertIntoTableValues.append(")");

            insertIntoTableQuery.append(insertIntoTableValues.toString());
            cachedInsertIntoQuery.put(clazz, insertIntoTableQuery.toString());
        }

        return cachedInsertIntoQuery.get(clazz);
    }

    public String getSelectByIdQuery(Class clazz){
        boolean addQuery= false;

        if (cachedSelectByIdQuery == null) {
            cachedSelectByIdQuery = new LinkedHashMap<>();
            addQuery = true;
        } else {
            addQuery = !cachedSelectByIdQuery.containsKey(clazz);
        }

        if (addQuery) {
            String tName = getTableNameForClass(clazz);
            StringBuilder selectByID = new StringBuilder();
            selectByID.append("select ");

            LinkedHashMap<String, Field> fields = getClassFields(clazz);
            for (Map.Entry entry : fields.entrySet()
            ) {
                Object fName = entry.getKey();
                selectByID.append(fName + ",");
            }
            selectByID.deleteCharAt(selectByID.lastIndexOf(","));
            selectByID.append(" from " + tName);
            selectByID.append(" where id = ?");

            cachedSelectByIdQuery.put(clazz, selectByID.toString());
        }
        return cachedSelectByIdQuery.get(clazz);
    }

    public String getTableNameForClass(Class clazz){
        if (cachedTableName == null){
            cachedTableName = new LinkedHashMap<Class, String>();
            cachedTableName.put(clazz, clazz.getName().replace(clazz.getPackageName()+".", ""));
        }
        return cachedTableName.get(clazz);
    }

    public List<Class> getConstructorParams(Class clazz){

        List<Class> constrParamsList = new ArrayList<Class>();
        LinkedHashMap<String, Field> fields = this.getClassFields(clazz);
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
