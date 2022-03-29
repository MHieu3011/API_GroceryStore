package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.BillDetailFormRequest;

public interface BillDetailService {

    Response create(BillDetailFormRequest form) throws Exception;
}
