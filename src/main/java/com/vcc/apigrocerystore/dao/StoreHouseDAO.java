package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.model.response.StoreHouseInfoItemResponse;

import java.util.List;

public interface StoreHouseDAO {
    void create(StoreHouseEntity entity) throws Exception;

    List<StoreHouseInfoItemResponse> findItemBestSeller(long fromDate, long toDate, String keyword, int limit) throws Exception;

}
