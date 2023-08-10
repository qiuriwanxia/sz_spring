package com.sz.spring.beans.factory;

import com.sun.istack.internal.Nullable;

import java.util.*;

public class ConstructorArgumentValues {

    private final Map<Integer, ValueHolder> indexedArgumentValues = new HashMap<>(0);
    private final List<ValueHolder> genericArgumentValues = new LinkedList<>();
    public ConstructorArgumentValues() {
    }
    private void addArgumentValue(Integer key, ValueHolder newValue) {
        this.indexedArgumentValues.put(key, newValue);
    }
    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }
    public ValueHolder getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }
    public void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentValues.add(new ValueHolder(value, type));
    }
    private void addGenericArgumentValue(ValueHolder newValue) {
        if (newValue.getName() != null) {
            for (Iterator<ValueHolder> it =
                 this.genericArgumentValues.iterator(); it.hasNext(); ) {
                ValueHolder currentValue = it.next();
                if (newValue.getName().equals(currentValue.getName())) {
                    it.remove();
                }
            }
        }
        this.genericArgumentValues.add(newValue);
    }
    public ValueHolder getGenericArgumentValue(String requiredName) {
        for (ValueHolder valueHolder : this.genericArgumentValues) {
            if (valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))) {
                continue;
            }
            return valueHolder;
        }
        return null;
    }
    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }
    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }


}
