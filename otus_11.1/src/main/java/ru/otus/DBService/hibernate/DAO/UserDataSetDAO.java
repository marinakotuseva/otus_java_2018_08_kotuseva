package ru.otus.DBService.hibernate.DAO;

import org.hibernate.Session;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDataSetDAO implements DAO{
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    @Override
    public void save(DataSet DataSet) {
        session.save(DataSet);
    }

    @Override
    public List<UserDataSet> loadAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }

    @Override
    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }
}
