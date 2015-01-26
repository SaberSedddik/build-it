package com.objectone.buildit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by saberseddik on 15-01-25.
 */
public class ObjectBuilder<T> {

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
        T builtObject = null;
        try {
            builtObject = clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return builtObject;
    }
}
