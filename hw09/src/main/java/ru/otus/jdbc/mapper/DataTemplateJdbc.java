package ru.otus.jdbc.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.crm.model.Client;

/** Сохратяет объект в базу, читает объект из базы */
@SuppressWarnings("java:S1068")
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    return new Client(rs.getLong("id"), rs.getString("name"));
                }
                return null;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
        return Optional.empty();
    }

    @Override
    public List<T> findAll(Connection connection) {
//        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
//                    var clientList = new ArrayList<Client>();
//                    try {
//                        while (rs.next()) {
//                            clientList.add(new Client(rs.getLong("id"), rs.getString("name")));
//                        }
//                        return clientList;
//                    } catch (SQLException e) {
//                        throw new DataTemplateException(e);
//                    }
//                })
//                .orElseThrow(() -> new RuntimeException("Unexpected error"));
        return List.of();
    }

    @Override
    public long insert(Connection connection, T client) {
         try {
            return dbExecutor.executeStatement(
                    connection, entitySQLMetaData.getInsertSql(), Collections.singletonList(client));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        try {
            dbExecutor.executeStatement(
                    connection, entitySQLMetaData.getUpdateSql(), List.of(client));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }
}
