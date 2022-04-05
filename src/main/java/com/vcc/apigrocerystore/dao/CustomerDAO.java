package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.CustomerEntity;
import com.vcc.apigrocerystore.model.response.InfoCustomerResponse;

public interface CustomerDAO {

    InfoCustomerResponse create(CustomerEntity entity) throws Exception;
}
