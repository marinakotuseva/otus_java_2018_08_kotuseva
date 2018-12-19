package ru.otus.DBService.DataSet;

import org.h2.engine.User;
import org.h2.store.Data;

import javax.persistence.*;

@Entity
@Table(name = "PhoneDataSetHibernate")
public class PhoneDataSet extends DataSet {
    @Column
    private String number;
    @ManyToOne
    private UserDataSet user;

    public PhoneDataSet(){}

    public PhoneDataSet(String number){
        this.number = number;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return number;
    }

}
