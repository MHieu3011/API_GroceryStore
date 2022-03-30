package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.ItemFormRequest;

public interface ItemService {

    Response create(ItemFormRequest form) throws Exception;

    Response findAll() throws Exception;
}
