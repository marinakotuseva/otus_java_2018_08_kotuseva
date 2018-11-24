package ru.otus;

import java.sql.*;

public class Executor {
    public  <T extends DataSet> void save(UserDataSet user) {
        String insertIntoUser = "insert into TestTable(id, name, age) values(?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","1Qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement s = conn.prepareStatement(insertIntoUser);
            s.setLong(1, user.getId());
            s.setString(2, user.getName());
            s.setInt(3, user.getAge());

            s.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    public  <T extends DataSet> UserDataSet load(long id, Class<T> myClass){

        Connection conn = null;
        UserDataSet loadedUser = null;
        try {
            conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","1Qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectByID = "select name, age from TestTable where id = ?";
        try {
            PreparedStatement s = conn.prepareStatement(selectByID);
            s.setLong(1, id);
            s.execute();
            ResultSet result = s.getResultSet();
            result.next();

            loadedUser = new UserDataSet(id, result.getString("name"), result.getInt("age"));
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


