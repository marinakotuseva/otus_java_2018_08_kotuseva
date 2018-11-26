package ru.otus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Executor {
    private Object object;

    public  <T extends DataSet> void save(T object) {
        Class clazz = object.getClass();
        String tName = getTableNameForClass(clazz);

        StringBuilder insertIntoTableQuery = new StringBuilder();
        StringBuilder insertIntoTableValues = new StringBuilder();

        insertIntoTableQuery.append("insert into " + tName);
        insertIntoTableQuery.append("(");
        insertIntoTableValues.append(" values(");

        LinkedHashMap<String, Object> fields = ClassFields.getClassFields(clazz);
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

        Connection conn = Connector.getConnection();

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
                if (fType == "long") {
                    s.setLong(i, (Long) fValue);
                } else if (fType == "java.lang.String") {
                    s.setString(i, (String) fValue);
                } else if (fType == "int") {
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
        //Class [] constrParams;
        //Object [] constrValues;

        List<Class> constrParamsList = new ArrayList<Class>();
        //where.add( ContactsContract.Contacts.HAS_PHONE_NUMBER+"=1" );
        //where.add( ContactsContract.Contacts.IN_VISIBLE_GROUP+"=1" );

        String tName = getTableNameForClass(clazz);

        Connection conn = Connector.getConnection();
        UserDataSet loadedUser = null;

        StringBuilder selectByID = new StringBuilder();
        selectByID.append("select ");

        LinkedHashMap<String, Object> fields = ClassFields.getClassFields(clazz);
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
            //f.setAccessible(true);
            Class fClass = f.getType();
            System.out.println("fClass="+fClass);
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
                System.out.println("132 " + fType);
                System.out.println("132 " + fType);
                if (fType == "long") {
                    sqlValue = result.getLong(fName);
                } else if (fType == "java.lang.String") {
                    sqlValue = result.getString(fName);
                } else {
                    sqlValue = result.getInt(fName);
                }
                constrParamsValues.add(sqlValue);
                System.out.println("sqlValue="+sqlValue);
            }

            Object[] constrValues = new Object[constrParamsValues.size() ];
            constrParamsValues.toArray(constrValues);
            for (int j = 0; j < constrValues.length; j++) {
                System.out.println(constrParams[j]);
                System.out.println(constrValues[j]);
            }

            //Class[] constrParams = { constrParamsList};
            //Object[] constrValues2 = {1, "Mike", 15};
            try {
                loadedUser = UserDataSet.class.getConstructor(constrParams).newInstance(constrValues);
                //System.out.println(u);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //loadedUser = new UserDataSet(id, result.getString(2), result.getInt(3));
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


