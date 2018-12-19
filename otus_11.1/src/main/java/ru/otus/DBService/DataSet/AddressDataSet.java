package ru.otus.DBService.DataSet;

import org.h2.engine.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AddressDataSetHibernate")
public class AddressDataSet extends DataSet {
    @Column
    private String street;
    @OneToOne
    private UserDataSet user;

    public AddressDataSet(){}

    public AddressDataSet(String street){
        this.street = street;
    }

    @Override
    public String toString(){
        return street;
    }


}
