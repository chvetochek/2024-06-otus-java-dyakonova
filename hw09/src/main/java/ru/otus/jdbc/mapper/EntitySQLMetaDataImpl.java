package ru.otus.jdbc.mapper;

import ru.otus.crm.model.Client;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData{
    EntityClassMetaData<Client> entityClassMetaDataClient;
    public EntitySQLMetaDataImpl(EntityClassMetaData<Client> entityClassMetaDataClient) {
        this.entityClassMetaDataClient = entityClassMetaDataClient;
    }

    @Override
    public String getSelectAllSql() {
        System.out.println("Select * from " + entityClassMetaDataClient.getName());

        return "Select * from " + entityClassMetaDataClient.getName();
    }

    @Override
    public String getSelectByIdSql() {
        System.out.println("Select * from " + entityClassMetaDataClient.getName() + " where " + entityClassMetaDataClient.getIdField().getName() + "=?");
        return "Select * from " + entityClassMetaDataClient.getName() + " where " + entityClassMetaDataClient.getIdField().getName() + "=?";
    }

    @Override
    public String getInsertSql() {
        System.out.println("Insert into "+ entityClassMetaDataClient.getName() + " (" +  entityClassMetaDataClient.getFieldsWithoutId().stream().map(Field::getName)
                .collect(Collectors.joining(", ", "", "")) + ") values (" + getValuesString(entityClassMetaDataClient.getFieldsWithoutId().size()) + ")");
        return "Insert into "+ entityClassMetaDataClient.getName() + " (" +  entityClassMetaDataClient.getFieldsWithoutId().stream().map(Field::getName)
                .collect(Collectors.joining(", ", "", "")) + ") values (" + getValuesString(entityClassMetaDataClient.getFieldsWithoutId().size()) + ")";

    }

    @Override
    public String getUpdateSql() {
        return "";
    }

    private String getValuesString(int count) {
        return Stream.generate(() -> "?")
                .limit(count)
                .collect(Collectors.joining(", "));
    }
}
