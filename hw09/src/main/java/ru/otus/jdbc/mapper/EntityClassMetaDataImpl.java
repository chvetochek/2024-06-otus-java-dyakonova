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
        return clazz.getConstructor();
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
