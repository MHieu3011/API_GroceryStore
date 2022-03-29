package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.BillFormRequest;

public interface BillService {

    Response create(BillFormRequest form) throws Exception;
}
