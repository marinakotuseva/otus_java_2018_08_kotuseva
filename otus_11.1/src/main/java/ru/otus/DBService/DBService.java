package ru.otus.DBService;

import org.hibernate.Session;
import ru.otus.DBService.DataSet.*;
import ru.otus.DBService.DataSet.UserDataSet;

import java.util.List;

public interface DBService <T extends DataSet> {

    void save(T DataSet) ;

    List<T> loadAll() ;

    T load(long id);

}
