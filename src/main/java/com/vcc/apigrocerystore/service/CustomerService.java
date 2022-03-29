package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.CustomerFormRequest;

public interface CustomerService {

    Response create(CustomerFormRequest form) throws Exception;
}
