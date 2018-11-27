package ru.otus.DataSet;


public class UserDataSet extends DataSet {



    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public UserDataSet(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }





}