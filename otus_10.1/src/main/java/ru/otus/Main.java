package ru.otus;


import ru.otus.DataSet.UserDataSet;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Executor ex = new Executor();

        DBService.createTableForClass(UserDataSet.class);
        DBService.clearTable(UserDataSet.class);
        System.out.println("===");

        for (int i = 0; i < 10; i++) {
            String name = "Name"+i;
            ex.save(new UserDataSet(i, name, i+10));
            System.out.println("User " + name + " saved");
        }

        System.out.println("===");
        UserDataSet loadedUser = ex.load(2, UserDataSet.class);
        System.out.println("user loaded: " + loadedUser.getName() + ", " + loadedUser.getAge());



    }
}
