package com.vcc.apigrocerystore.adapter;

public interface EntityAdapter<T, R> {
    /**
     * perform an operation with entity
     *
     * @param entity entity to perform
     * @return response entity for client
     */
    R transform(T entity);
}
