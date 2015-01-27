package com.objectone.buildit;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saberseddik on 15-01-25.
 */
public class ObjectBuilder<T> implements Cloneable {

    private Map<String, Object> fieldsValues;
    private Class<T> clazz;

    ObjectBuilder(Class<T> clazz) {
        this.clazz = clazz;
        fieldsValues = new HashMap<String, Object>();
    }

    public ObjectBuilder<T> having(String name, Object object) {
        ObjectBuilder<T> copy = new ObjectBuilder<T>(clazz);
        copy.copyFieldsValue(this);
        copy.fieldsValues.put(name, object);
        return copy;
    }

    private void copyFieldsValue(ObjectBuilder<T> objectBuilder) {
        this.fieldsValues.putAll(objectBuilder.fieldsValues);
    }

    public T build() {
        T builtObject = newInstance();
        for (String field : fieldsValues.keySet()) {
            populateField(field, builtObject);
        }
        return builtObject;
    }

    private void populateField(String field, T destination) {
        Field fieldObject = null;
        try {
            fieldObject = clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        setField(fieldObject, destination, fieldsValues.get(field));
    }

    private void setField(Field field, T destination, Object value) {
        field.setAccessible(true);
        try {
            field.set(destination, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private T newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
