package ru.otus.DBService.myORM;

import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Executor {

    public  <T extends DataSet> void save(T object) {

        Class clazz = object.getClass();
        String insertIntoTableQuery = ClassMetaDataHolder.getInsertIntoTableQuery(clazz);
        LinkedHashMap<String, Field> fields = ClassMetaDataHolder.getClassFields(clazz);

        Connection conn = MyOrmDBHelper.getConnection();
        try {
            PreparedStatement s = conn.prepareStatement(insertIntoTableQuery);
            int i =1;
            for (Map.Entry entry: fields.entrySet()
            ) {
                Field f = (Field)entry.getValue();
                f.setAccessible(true);
                Object fValue = f.get(object);
                String fName = f.getName();
                if (fName == "id") {
                    s.setLong(i, (Long) fValue);
                } else if (fName == "name") {
                    s.setString(i, (String) fValue);
                } else if (fName == "age") {
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
        List<Class> constrParamsList = ClassMetaDataHolder.getConstructorParams(clazz);
        String SelectByIdQuery = ClassMetaDataHolder.getSelectByIdQuery(clazz);


        Connection conn = MyOrmDBHelper.getConnection();
        try {
            PreparedStatement s = conn.prepareStatement(SelectByIdQuery);
            s.setLong(1, id);
            s.execute();
            ResultSet result = s.getResultSet();
            result.next();

            Class[] constrParams = new Class[constrParamsList.size() ];
            constrParams = constrParamsList.toArray(constrParams);

            List<Object> constrParamsValues = new ArrayList<Object>();
            LinkedHashMap<String, Field> fields = ClassMetaDataHolder.getClassFields(clazz);
            for (Map.Entry entry: fields.entrySet()){
                Object sqlValue;
                String fName = (String)entry.getKey();
                Field f = (Field)entry.getValue();
                Object fType = f.getType();
                if (fType == long.class) {
                    sqlValue = result.getLong(fName);
                    constrParamsValues.add(sqlValue);
                } else if (fType == String.class) {
                    sqlValue = result.getString(fName);
                    constrParamsValues.add(sqlValue);
                } else if (fType == int.class){
                    sqlValue = result.getInt(fName);
                    constrParamsValues.add(sqlValue);
                }
            }

            Object[] constrValues = new Object[constrParamsValues.size() ];
            constrParamsValues.toArray(constrValues);

            try {
                Constructor<UserDataSet> c = UserDataSet.class.getConstructor(constrParams);
                loadedUser = c.newInstance(constrValues);
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


