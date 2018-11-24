package ru.otus;

import org.reflections.Reflections;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String drop = "drop table TestTable";
        String createTable =  "create table TestTable " +
                "(id bigint(20) not null auto_increment, " +
                " name varchar(255), " +
                " age int(3))";

        Class.forName ("org.h2.Driver");
        try {
            Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","1Qwerty");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(drop);
            System.out.println("Table dropped");

            stmt.executeUpdate(createTable);
            System.out.println("Table created");
            conn.commit();
            conn.close();

            UserDataSet user = new UserDataSet(1, "Jack", 10);
            UserDataSet user2 = new UserDataSet(2, "Daniel", 13);

            Executor ex = new Executor();
            ex.save(user);
            System.out.println("user added");
            ex.save(user2);
            System.out.println("user added");

            UserDataSet loadedUser = ex.load(2, UserDataSet.class);
            System.out.println("user loaded: " + loadedUser.getName() + ", " + loadedUser.getAge());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
