package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.ItemEntity;

import java.util.List;

public interface ItemDAO {
    void create(ItemEntity entity) throws Exception;

    List<ItemEntity> findAll() throws Exception;
}
