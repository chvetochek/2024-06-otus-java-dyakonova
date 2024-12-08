package ru.otus.jdbc.mapper;

import ru.otus.crm.model.Client;

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
        System.out.println("Select * from " + entityClassMetaDataClient.getName() + " where " + entityClassMetaDataClient.getIdField().getName() + "=" + entityClassMetaDataClient.getIdField().toString());
        return "Select * from " + entityClassMetaDataClient.getName() + " where " + entityClassMetaDataClient.getIdField().getName() + "=" + entityClassMetaDataClient.getIdField().toString();
    }

    @Override
    public String getInsertSql() {
        return "";
    }

    @Override
    public String getUpdateSql() {
        return "";
    }
}
