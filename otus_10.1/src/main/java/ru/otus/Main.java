package ru.otus;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName ("org.h2.Driver");
        Executor ex = new Executor();

        DBService.createTableForClass(UserDataSet.class);
        DBService.clearTable(UserDataSet.class);



        for (int i = 0; i < 10; i++) {
            String name = "Name"+i;
            ex.save(new UserDataSet(i, name, i+10));
            System.out.println("User " + name + " saved");
        }

        UserDataSet loadedUser = ex.load(2, UserDataSet.class);
        System.out.println("user loaded: " + loadedUser.getName() + ", " + loadedUser.getAge());

//        System.out.println("=====");
//        Class [] constrParams = { long.class, String.class, int.class};
//        Object [] constrValues = {  1, "Mike", 15};
//        try {
//            UserDataSet u = UserDataSet.class.getConstructor(constrParams).newInstance(constrValues);
//            System.out.println(u);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

    }
}
