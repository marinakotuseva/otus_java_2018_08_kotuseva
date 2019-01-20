package ru.otus.DBService;

import ru.otus.DBService.DataSet.DataSet;

import java.util.List;

public interface DBService <T extends DataSet> {

    void save(T DataSet) ;

    List<T> loadAll() ;

    T load(long id);

}
