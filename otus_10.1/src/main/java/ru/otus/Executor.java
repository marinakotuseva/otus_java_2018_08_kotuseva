package ru.otus;

import ru.otus.DataSet.DataSet;
import ru.otus.DataSet.UserDataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Executor {

    public  <T extends DataSet> void save(T object) {
        Class clazz = object.getClass();
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

        Connection conn = DBService.getConnection();

        try {
            String q = insertIntoTableQuery.toString();
            PreparedStatement s = conn.prepareStatement(q);
            var i =1;

            for (Map.Entry entry: fields.entrySet()
            ) {
                Object fName = entry.getKey();
                Object fType = entry.getValue();
                Field f = clazz.getDeclaredField((String)fName);
                f.setAccessible(true);
                Object fValue = f.get(object);
                if (fType == long.class) {
                    s.setLong(i, (Long) fValue);
                } else if (fType == String.class) {
                    s.setString(i, (String) fValue);
                } else if (fType == int.class) {
                    s.setInt(i, (Integer) fValue);
                }
                i++;
            }

            s.executeUpdate();
            conn.commit();

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            System.out.println("Field is not found");
        }
    }
    public  <T extends DataSet> UserDataSet load(long id, Class<T> clazz){

        List<Class> constrParamsList = new ArrayList<Class>();

        String tName = getTableNameForClass(clazz);

        Connection conn = DBService.getConnection();
        UserDataSet loadedUser = null;

        StringBuilder selectByID = new StringBuilder();
        selectByID.append("select ");

        LinkedHashMap<String, Class> fields = ClassMetaDataHolder.getClassFields(clazz);
        for (Map.Entry entry: fields.entrySet()
        ) {
            Object fName = entry.getKey();
            selectByID.append(fName + ",");

            Field f = null;
            try {
                f = clazz.getDeclaredField((String)fName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            Class fClass = f.getType();
            constrParamsList.add(fClass);
        }

        selectByID.deleteCharAt(selectByID.lastIndexOf(","));

        selectByID.append(" from " + tName);
        selectByID.append(" where id = ?");

        try {
            PreparedStatement s = conn.prepareStatement(selectByID.toString());
            s.setLong(1, id);
            s.execute();
            ResultSet result = s.getResultSet();
            result.next();

            Class[] constrParams = new Class[constrParamsList.size() ];
            constrParams = constrParamsList.toArray(constrParams);

            List<Object> constrParamsValues = new ArrayList<Object>();
            int i = 1;
            for (Map.Entry entry: fields.entrySet()){
                Object sqlValue;
                String fName = (String)entry.getKey();
                Object fType = entry.getValue();
                if (fType == Long.class) {
                    sqlValue = result.getLong(fName);
                } else if (fType == String.class) {
                    sqlValue = result.getString(fName);
                } else {
                    sqlValue = result.getInt(fName);
                }
                constrParamsValues.add(sqlValue);
            }

            Object[] constrValues = new Object[constrParamsValues.size() ];
            constrParamsValues.toArray(constrValues);

            try {
                loadedUser = UserDataSet.class.getConstructor(constrParams).newInstance(constrValues);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return loadedUser;
    }
    public static String getTableNameForClass(Class clazz){
        return clazz.getName().replace(clazz.getPackageName()+".", "");
    }

}


