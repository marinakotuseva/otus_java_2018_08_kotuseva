package ru.otus;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedHashMap;
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
        }

        selectByID.deleteCharAt(selectByID.lastIndexOf(","));

        selectByID.append(" from " + tName);
        selectByID.append(" where id = ?");

        //String selectByID = "select name, age from TestTable where id = ?";
        try {
            PreparedStatement s = conn.prepareStatement(selectByID.toString());
            s.setLong(1, id);
            s.execute();
            ResultSet result = s.getResultSet();
            result.next();

            loadedUser = new UserDataSet(id, result.getString(2), result.getInt(3));
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


