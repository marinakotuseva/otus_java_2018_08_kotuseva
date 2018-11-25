package ru.otus;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connector {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","1Qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
