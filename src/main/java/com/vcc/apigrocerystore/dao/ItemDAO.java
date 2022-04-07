package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.model.response.InfoItemResponse;

import java.util.List;

public interface ItemDAO {
    InfoItemResponse create(ItemEntity entity) throws Exception;

    List<ItemEntity> findAll() throws Exception;

    boolean checkItemByIdAndCode(long id, String code) throws Exception;
}
