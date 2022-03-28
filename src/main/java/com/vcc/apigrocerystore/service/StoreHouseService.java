package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.StoreHouseFormRequest;

public interface StoreHouseService {

    Response create(StoreHouseFormRequest form) throws Exception;
}
