package ru.otus.DBService.myORM;

import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.MessageSystem;

import java.util.List;

public class MyOrmDBServiceImpl implements DBService {
    private Address address;
    private MessageSystem messageSystem;

    @Override
    public void save(DataSet DataSet) {
        Executor ex = new Executor();
        ex.save(DataSet);
    }

    @Override
    public List loadAll() {
        return null;
    }

    @Override
    public UserDataSet load(long id) {
        Executor ex = new Executor();
        UserDataSet loadedUser = ex.load(id, UserDataSet.class);
        return loadedUser;
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return this.messageSystem;
    }
}
