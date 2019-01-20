package ru.otus.DBService.hibernate.DAO;

import org.hibernate.Session;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.PhoneDataSet;

import java.util.List;

public class PhoneDataSetDAO implements DAO {
    private Session session;

    public PhoneDataSetDAO(Session session) {
        this.session = session;
    }

    @Override
    public void save(DataSet DataSet) {
        session.save(DataSet);
    }

    @Override
    public List<PhoneDataSet> loadAll() {
        List<PhoneDataSet> phones = session.createQuery("from PhoneDataSet").list();
        return phones;
    }

    @Override
    public PhoneDataSet load(long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
