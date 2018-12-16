package ru.otus.DBService.hibernate;

import org.hibernate.Session;
import ru.otus.DBService.DataSet.UserDataSet;

import java.sql.SQLException;
import java.util.List;

public class DAO {
    private Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public void save(Object dataSet) {
        session.beginTransaction();
        session.save(dataSet);
        session.getTransaction().commit();
    }

    public void loadAll(Class clazz) {
        //@SuppressWarnings("unchecked")
        String tableName = clazz.getSimpleName();
        System.out.println("tableName="+tableName);
        List<UserDataSet> dataSet = session.createQuery("from " + tableName).list();
        System.out.println(dataSet.size());
        if(dataSet != null){
            dataSet.forEach((x) -> System.out.printf("-- " + tableName + ' ' + x.toString()));
            //dataSet.forEach((x) -> System.out.print(x.getID()));
        }
    }

    public void loadByID(Class clazz,long id) throws SQLException {
        String tableName = clazz.getSimpleName();
        List<UserDataSet> users = session.createQuery("from "+ tableName +" where id =" + id).list();
        if (users.size()>1){
            //todo make custom exception
            throw new SQLException();
        }
        users.forEach((u) -> System.out.printf("-- " + tableName + ' ' + u.toString()));
    }

}
