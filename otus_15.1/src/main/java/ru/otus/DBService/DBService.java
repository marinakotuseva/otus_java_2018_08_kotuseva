package ru.otus.DBService;

import ru.otus.DBService.DataSet.DataSet;
import ru.otus.MessageSystem.Sender;

import java.util.List;

public interface DBService extends Sender {
    <T extends DataSet> void save(T DataSet);
    <T extends DataSet> T load(long id);
    List loadAll();

}
