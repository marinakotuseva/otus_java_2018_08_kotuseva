package ru.otus;


import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.AddressDataSet;
import ru.otus.DBService.DataSet.PhoneDataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.hibernate.HibernateDBServiceImpl;
import ru.otus.DBService.myORM.MyOrmDBHelper;
import ru.otus.DBService.myORM.MyOrmDBServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Create table fro myORM
        MyOrmDBHelper MyOrmDBHelper = new MyOrmDBHelper();
        MyOrmDBHelper.createTableForClass(UserDataSet.class);
        MyOrmDBHelper.clearTable(UserDataSet.class);


        // UserList
        List<UserDataSet> userList = new ArrayList<>();
        UserDataSet user1 = new UserDataSet(1, "Jack", 10);


        userList.add(user1);
        userList.add(new UserDataSet(2, "Kyle", 13));
        userList.add(new UserDataSet(3, "Agatha", 17));

        // Via MyORM
        System.out.println("=== MyORM===");
        DBService myOrmDBService = new MyOrmDBServiceImpl();
        for (UserDataSet user : userList
        ) {
            myOrmDBService.save(user);
            System.out.println("User saved: " + user.toString());
        }

        UserDataSet loadedUserMyORM = ((MyOrmDBServiceImpl) myOrmDBService).load(2);
        System.out.println("User loaded: " + loadedUserMyORM.toString());

        // Via hibernate/
        user1.setAddress(new AddressDataSet("Baker street"));
        List<PhoneDataSet> phones = new ArrayList<>();
        PhoneDataSet ph1 = new PhoneDataSet("111");
        PhoneDataSet ph2 = new PhoneDataSet("222");
        List ph = new ArrayList();
        ph.add(ph1);
        ph.add(ph2);
        user1.setPhones(ph);
        ph1.setUser(user1);
        ph2.setUser(user1);
        System.out.println("=== Via hibernate ===");
        DBService hibernateDBService = new HibernateDBServiceImpl();
        for (UserDataSet user : userList
        ) {
            hibernateDBService.save(user);
            System.out.println("User saved: " + user.toString());
        }

        //UserDataSet loadedUserHibernate = hibernateDBService.load(1);
        //System.out.println("User loaded: " + loadedUserHibernate.toString());

        List<UserDataSet> list = hibernateDBService.loadAll();
        System.out.println(list);
        for (UserDataSet user : list
        ) {
            System.out.println("All users loaded: " + user.toString());
        }
        List<AddressDataSet> addresses = hibernateDBService.loadAll();
        System.out.println(list);
        for (UserDataSet user : list
        ) {
            System.out.println("All users loaded: " + user.toString());
        }
    }




}
