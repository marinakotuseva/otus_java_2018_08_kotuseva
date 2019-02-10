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
        ClassMetaDataHolder classData = new ClassMetaDataHolder();
        String insertIntoTableQuery = classData.getInsertIntoTableQuery(clazz);
        LinkedHashMap<String, Field> fields = classData.getClassFields(clazz);

        Connection conn = DBHelper.getConnection();
        try {
            PreparedStatement s = conn.prepareStatement(insertIntoTableQuery);
            var i =1;
            for (Map.Entry entry: fields.entrySet()
            ) {
                Field f = (Field)entry.getValue();
                Object fType = f.getType();
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
        }
    }
    public  <T extends DataSet> UserDataSet load(long id, Class<T> clazz){

        UserDataSet loadedUser = null;
        ClassMetaDataHolder classData = new ClassMetaDataHolder();
        List<Class> constrParamsList = classData.getConstructorParams(clazz);
        String SelectByIdQuery = classData.getSelectByIdQuery(clazz);


        Connection conn = DBHelper.getConnection();
        try {
            PreparedStatement s = conn.prepareStatement(SelectByIdQuery);
            s.setLong(1, id);
            s.execute();
            ResultSet result = s.getResultSet();
            result.next();

            Class[] constrParams = new Class[constrParamsList.size() ];
            constrParams = constrParamsList.toArray(constrParams);

            List<Object> constrParamsValues = new ArrayList<Object>();
            LinkedHashMap<String, Field> fields = classData.getClassFields(clazz);
            for (Map.Entry entry: fields.entrySet()){
                Object sqlValue;
                String fName = (String)entry.getKey();
                Field f = (Field)entry.getValue();
                Object fType = f.getType();
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

}


