//package ru.otus.DBService.DBService.DataSet;
//
//import DataSet;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "UserDataSet")
//public class UserDataSet extends DataSet {
//
//    @Column(name = "name")
//    private String name;
//
//
//    //Important for Hibernate
//    public UserDataSet() {
//    }
//
//    public UserDataSet(String name) {
//        this.setId(-1);
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    private void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDataSet{" +
//                "id'" + getId() + '\'' +
//                "name='" + name + '\'' +
//                '}';
//    }
//}

package ru.otus.DBService.DataSet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserDataSet")
public class UserDataSet extends DataSet {
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "user")
    private AddressDataSet address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneDataSet> phones;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public long getID() {
        return id;
    }
    public String getAddress() {
        return address.toString();
    }

    public UserDataSet(){}

    public UserDataSet(String name, int age) {
        this.id = 0;
        this.name = name;
        this.age = age;
        this.address = null;
        this.phones = new ArrayList<>();
    }

    public UserDataSet(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = null;
        this.phones = new ArrayList<>();
    }
    public UserDataSet(long id, String name, int age, AddressDataSet address, List phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;
    }

    //public void setAddress(AddressDataSet address){
    //    this.address = address;
    //}

    public void setPhone(PhoneDataSet phone) {
        phones.add(phone);
        phone.setUser(this);
    }
    public void setAddress(AddressDataSet address){
        this.address = address;
    }


    @Override
    public String toString(){
        String ph = "";
        if (phones != null){
            for (PhoneDataSet p: phones
            ) {
                ph+= p.toString() + ", ";
            }
        }

        return "id=" + id + ", " +
        "name=" + name + ", " +
        "age=" + age + ", " +
        "address=" + address + ", "+
        "phones="+ph;

    }
}
