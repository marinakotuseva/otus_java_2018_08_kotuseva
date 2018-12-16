package ru.otus;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.DBService.DataSet.AddressDataSet;
import ru.otus.DBService.DataSet.PhoneDataSet;
import ru.otus.DBService.hibernate.DBServiceImpl;
import ru.otus.DBService.DataSet.UserDataSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        DBServiceImpl dbService = new DBServiceImpl();
        // TODO: not working
//        String status =  dbService.getLocalStatus();
//        System.out.println("Current Status: " + status);

        SessionFactory sessionFactory = dbService.getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println("-- sessions opened --");
        try {
            List<UserDataSet> userList = new ArrayList<>();
            UserDataSet user1 = new UserDataSet(1, "Jack", 10);
            user1.setAddress(new AddressDataSet("Baker street"));
            List<PhoneDataSet> phones = new ArrayList<>();
            phones.add(new PhoneDataSet("111"));
            phones.add(new PhoneDataSet("222"));
            user1.setPhones(phones);
            userList.add(user1);
            userList.add(new UserDataSet(2, "Kyle", 13));
            userList.add(new UserDataSet(3, "Agatha", 17));

            System.out.println("-- saving users to database --");
            for (UserDataSet user : userList
            ) {
                DBServiceImpl.save(session, user);
            }

            DBServiceImpl.loadAll(session, UserDataSet.class);
            // Hibernate errors not Exceptions so no catch?

            DBServiceImpl.loadByID(session, UserDataSet.class, 2);

        } finally {
            sessionFactory.close();
            System.out.println("-- sessions closed --");
        }
    }




}
