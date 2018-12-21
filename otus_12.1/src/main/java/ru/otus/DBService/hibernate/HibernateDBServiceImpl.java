package ru.otus.DBService.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.hibernate.DAO.UserDataSetDAO;

import java.util.List;

public class HibernateDBServiceImpl implements DBService {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public HibernateDBServiceImpl() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(DataSet DataSet) {
        Session session = sessionFactory.openSession();
        UserDataSetDAO dao = new UserDataSetDAO(session);
        session.beginTransaction();
        dao.save(DataSet);
        session.getTransaction().commit();
    }

    @Override
    public List loadAll() {
        Session session = sessionFactory.openSession();
        UserDataSetDAO dao = new UserDataSetDAO(session);
        List<UserDataSet> list = dao.loadAll();
        return list;
    }

    @Override
    public UserDataSet load(long id) {
        Session session = sessionFactory.openSession();
        UserDataSetDAO dao = new UserDataSetDAO(session);
        UserDataSet loadedUser = dao.load(id);
        return loadedUser;
    }

//    // TODO: where create sessions - here or outside?
//    public <T extends DataSet> void save(T DataSet) {
//        Session session = sessionFactory.openSession();
//        session.save(object);
//    }
//    public static void load(Session session, Class clazz, long id) throws SQLException {
//        DAO dao = new DAO(session);
//        dao.loadByID(clazz, id);
//    }
//
//    public <T extends DataSet> UserDataSet load(long id, Class<T> clazz){
//        Session session = sessionFactory.openSession();
//        UserDataSet loadedUser = session.load(UserDataSet);
//        return loadedUser;
//    }
//
//    public static void loadAll(Session session, Class clazz) {
//        DAO dao = new DAO(session);
//        dao.loadAll(clazz);
//
//    }


}