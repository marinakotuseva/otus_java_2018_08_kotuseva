//package ru.otus.DBService.hibernate;
//
//import org.hibernate.Session;
//import ru.otus.DBService.DataSet.UserDataSet;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserDataSetDAO {
//    private Session session;
//
//    public UserDataSetDAO(Session session) {
//        this.session = session;
//    }
//
//    public void save(UserDataSet user) {
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//    }
//
//    public void loadAllUsers() {
//        //@SuppressWarnings("unchecked")
//        List<UserDataSet> users = session.createQuery("from UserDataSet").list();
//        users.forEach((u) -> System.out.printf("-- User " + u.toString()));
//    }
//
//    public void loadByID(long id) throws SQLException {
//        List<UserDataSet> users = session.createQuery("from UserDataSet where id =" + id).list();
//        if (users.size()>1){
//            //todo make custom exception
//            throw new SQLException();
//        }
//        users.forEach((u) -> System.out.printf("-- User " + u.toString()));
//    }
//
//}
