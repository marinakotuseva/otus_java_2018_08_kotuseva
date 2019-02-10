package ru.otus.DBService.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.cache.CacheEngine;
import ru.otus.DBService.hibernate.DAO.UserDataSetDAO;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.MessageSystem;

import java.util.List;

public class HibernateDBServiceImpl implements DBService {
    private SessionFactory sessionFactory;
    private CacheEngine cachedUsers;
    private Address adress;
    private MessageSystem messageSystem;

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
//        if(cachedUsers == null) {
//            ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
//            cachedUsers = context.getBean("cacheEngine", CacheEngine.class);
//        }
        //UserDataSet loadedUser = (UserDataSet) cachedUsers.get(id);
        //if (loadedUser == null){
            Session session = sessionFactory.openSession();
            UserDataSetDAO dao = new UserDataSetDAO(session);
            UserDataSet loadedUser = dao.load(id);
        //}
        return loadedUser;

    }

    @Override
    public Address getAddress() {
        return this.adress;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return this.messageSystem;
    }
}