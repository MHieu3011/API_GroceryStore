package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.model.response.InfoItemBestSellerResponse;
import com.vcc.apigrocerystore.model.response.InfoItemByExpireResponse;

import java.util.List;

public interface StoreHouseDAO {
    void create(StoreHouseEntity entity) throws Exception;

    List<InfoItemBestSellerResponse> findItemBestSeller(long fromDate, long toDate, String keyword, int limit) throws Exception;

    List<InfoItemByExpireResponse> findItemByExpire(long fromDate, long toDate) throws Exception;

    List<InfoItemByExpireResponse> findItemByExpire(int limit) throws Exception;
}
