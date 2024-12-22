package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T>{
    Class<T> clazz;
    public EntityClassMetaDataImpl(Class<T> clientClass) {
        clazz = clientClass;
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() throws NoSuchMethodException {
        var fields = getAllFields();
        Class<?>[] parameterTypes = new Class[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            parameterTypes[i] = fields.get(i).getType();
        }
        return clazz.getConstructor(parameterTypes);
    }

    @Override
    public Field getIdField() {
        for (var field : getAllFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return new ArrayList<>(List.of(clazz.getDeclaredFields()));
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        var idField = getIdField();
        var allFields = getAllFields();
        if (idField != null) {
            allFields.remove(idField);
        }
        return allFields;
    }
}
