package ru.otus;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBService {

    public static void createTableForClass(Class clazz) {
        StringBuilder q = new StringBuilder();
        String tName = Executor.getTableNameForClass(clazz);
        q.append("create table if not exists " + tName);
        q.append(" (");

        LinkedHashMap<String, Object> fields = ClassFields.getClassFields(clazz);
        for (Map.Entry entry: fields.entrySet()
             ) {
            Object fName = entry.getKey();
            Object fType = entry.getValue();
            q.append(fName);
            if (fName == "id") {
                q.append(" bigint(20) not null auto_increment");
            } else if (fType == "java.lang.String") {
                q.append(" varchar(255)");
            } else if (fType == "int") {
                q.append(" int(3)");
            }
            q.append(",");
        }
        q.deleteCharAt(q.lastIndexOf(","));
        q.append(")");


        Connection conn = Connector.getConnection();
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
    public static void clearTable(Class clazz){
        String tName = Executor.getTableNameForClass(clazz);
        Connection conn = Connector.getConnection();
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