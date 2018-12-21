package ru.otus.DBService.hibernate.DAO;

import org.hibernate.Session;
import ru.otus.DBService.DataSet.AddressDataSet;
import ru.otus.DBService.DataSet.DataSet;

import java.util.List;

public class AddressDataSetDAO implements DAO {
    private Session session;

    public AddressDataSetDAO(Session session) {
        this.session = session;
    }

    @Override
    public void save(DataSet DataSet) {
        session.save(DataSet);
    }

    @Override
    public List<AddressDataSet> loadAll() {
        List<AddressDataSet> addresses = session.createQuery("from AddressDataSet").list();
        return addresses;
    }

    @Override
    public AddressDataSet load(long id) {
        return session.load(AddressDataSet.class, id);
    }
}
