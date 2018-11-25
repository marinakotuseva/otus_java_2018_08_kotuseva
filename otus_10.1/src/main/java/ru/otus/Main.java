package ru.otus;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName ("org.h2.Driver");
        Executor ex = new Executor();

        DBService.createTableForClass(UserDataSet.class);
        DBService.clearTable(UserDataSet.class);



        for (int i = 0; i < 10; i++) {
            String name = "Name"+i;
            ex.save(new UserDataSet(i, name, i+10));
            System.out.println("User " + name + " saved");
        }
//        System.out.println("user added");
//        ex.save(user2);
//        System.out.println("user added");
//
        UserDataSet loadedUser = ex.load(2, UserDataSet.class);
        System.out.println("user loaded: " + loadedUser.getName() + ", " + loadedUser.getAge());

    }
}
