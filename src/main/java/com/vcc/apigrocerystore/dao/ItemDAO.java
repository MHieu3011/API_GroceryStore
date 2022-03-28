package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.ItemEntity;

public interface ItemDAO {
    void create(ItemEntity entity) throws Exception;
}
