//package ru.otus.DBService.DataSet;
//
//import ru.otus.DBService.DataSet.DataSet;
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

import org.h2.engine.User;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private AddressDataSet address;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
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

    public UserDataSet(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setAddress(AddressDataSet address){
        this.address = address;
    }

    public void setPhones(List<PhoneDataSet> phones){
        this.phones = phones;
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
        "phones="+ph + "\n";

    }
}
