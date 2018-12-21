package ru.otus.DBService.hibernate.DAO;

import ru.otus.DBService.DataSet.DataSet;

import java.util.List;

public interface DAO <T extends DataSet>{

    void save(T DataSet) ;

    List<T> loadAll() ;

    T load(long id);

}
