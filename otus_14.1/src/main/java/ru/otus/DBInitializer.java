package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.AddressDataSet;
import ru.otus.DBService.DataSet.PhoneDataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.cache.CacheEngine;
import ru.otus.DBService.hibernate.HibernateDBServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DBInitializer {
    private DBService hibernateDBService;

    public DBInitializer(DBService hibernateDBService){

        this.hibernateDBService = hibernateDBService;
    }

    public DBService InitializeDB() {

        // UserList
        List<UserDataSet> userList = new ArrayList<>();
        UserDataSet user1 = new UserDataSet(1, "Jack", 10);
        userList.add(user1);
        userList.add(new UserDataSet(2, "Kyle", 13));
        userList.add(new UserDataSet(3, "Agatha", 17));

//        // Via MyORM
//        // Create table for myORM
//        MyOrmDBHelper MyOrmDBHelper = new MyOrmDBHelper();
//        MyOrmDBHelper.createTableForClass(UserDataSet.class);
//        MyOrmDBHelper.clearTable(AddressDataSet.class);
//        MyOrmDBHelper.clearTable(PhoneDataSet.class);
//        MyOrmDBHelper.clearTable(UserDataSet.class);
//        System.out.println("=== MyORM===");
//        DBService myOrmDBService = new MyOrmDBServiceImpl();
//        for (UserDataSet user : userList
//        ) {
//            myOrmDBService.save(user);
//            System.out.println("User saved: " + user.toString());
//        }
//
//        UserDataSet loadedUserMyORM = ((MyOrmDBServiceImpl) myOrmDBService).load(2);
//        System.out.println("User loaded: " + loadedUserMyORM.toString());


        // Via hibernate/
        AddressDataSet ad1 = new AddressDataSet("Baker street");
        ad1.setUser(user1);
        user1.setAddress(ad1);
        List<PhoneDataSet> phones = new ArrayList<>();
        PhoneDataSet ph1 = new PhoneDataSet("111");
        PhoneDataSet ph2 = new PhoneDataSet("222");
        user1.setPhone(ph1);
        user1.setPhone(ph2);

        System.out.println("=== Via hibernate ===");
        //DBService hibernateDBService = new HibernateDBServiceImpl();
        System.out.println("dbService BEAN " + hibernateDBService);

        System.out.println("===SAVING===");
        for (UserDataSet user : userList
        ) {
            hibernateDBService.save(user);
            System.out.println("User saved: " + user.toString());
        }

        UserDataSet loadedUserHibernate = ((HibernateDBServiceImpl) hibernateDBService).load(1);
        System.out.println("User loaded by id: " + loadedUserHibernate.toString());

        List<UserDataSet> list = hibernateDBService.loadAll();
        for (UserDataSet user : list
        ) {
            System.out.println("All users loaded: " + user.toString());
        }

        return hibernateDBService;
    }
}