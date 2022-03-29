package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.CustomerEntity;

public interface CustomerDAO {

    void create(CustomerEntity entity) throws Exception;
}
