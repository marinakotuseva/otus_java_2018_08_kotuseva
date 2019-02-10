package ru.otus.DBService.myORM;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyOrmDBHelper {


    static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","1Qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void createTableForClass(Class clazz) {
        StringBuilder q = new StringBuilder();
        String tName = ClassMetaDataHolder.getTableNameForClass(clazz);
        q.append("create table if not exists " + tName);
        q.append(" (");

        LinkedHashMap<String, Field> fields = ClassMetaDataHolder.getClassFields(clazz);
        for (Map.Entry entry: fields.entrySet()
             ) {
            Object fName = entry.getKey();
            Field f = (Field) entry.getValue();
            if (fName == "id") {
                q.append(fName);
                q.append(" bigint(20) not null auto_increment");
                q.append(",");
            } else if (fName == "name") {
                q.append(fName);
                q.append(" varchar(255)");
                q.append(",");
            } else if (fName == "age") {
                q.append(fName);
                q.append(" int(3)");
                q.append(",");
            }
        }
        q.deleteCharAt(q.lastIndexOf(","));
        q.append(")");


        Connection conn = getConnection();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(q.toString());
            System.out.println("Table " + tName + " created");

            conn.commit();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public void clearTable(Class clazz){
        String tName = ClassMetaDataHolder.getTableNameForClass(clazz);
        Connection conn = getConnection();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from " + tName);
            System.out.println("Table " + tName + " cleared");

            conn.commit();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
