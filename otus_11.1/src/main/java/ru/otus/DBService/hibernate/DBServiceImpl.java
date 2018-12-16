package ru.otus.DBService.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.DBService.DataSet.AddressDataSet;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.myORM.DBService;

import java.sql.SQLException;
import java.util.List;

public class DBServiceImpl implements DBService {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public DBServiceImpl() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        this.sessionFactory = sessionFactory;
    }

    // TODO: where create sessions - here or outside?
    public static void save(Session session, UserDataSet user) {
        DAO dao = new DAO(session);
        dao.save(user);
    }
    public static void save(Session session, Object dataSet) {
        DAO dao = new DAO(session);
        dao.save(dataSet);
    }

    public static void loadAll(Session session, Class clazz) {
        DAO dao = new DAO(session);
        dao.loadAll(clazz);

    }

    public static void loadByID(Session session, Class clazz, long id) throws SQLException {
        DAO dao = new DAO(session);
        dao.loadByID(clazz, id);
    }

}