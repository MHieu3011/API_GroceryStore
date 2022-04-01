package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.BillFormRequest;
import com.vcc.apigrocerystore.model.request.BillRegistrationFormRequest;

public interface BillService {

    Response createByParam(BillFormRequest form) throws Exception;

    Response create(BillRegistrationFormRequest form) throws Exception;

    Response getTotalRevenueByBrand(BillFormRequest form) throws Exception;
}
