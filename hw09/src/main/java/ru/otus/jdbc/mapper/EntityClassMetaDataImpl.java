package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class EntityClassMetaDataImpl implements EntityClassMetaData{
    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public Constructor getConstructor() throws NoSuchMethodException {
        return getClass().getConstructor();
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
        return List.of(getClass().getDeclaredFields());
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
