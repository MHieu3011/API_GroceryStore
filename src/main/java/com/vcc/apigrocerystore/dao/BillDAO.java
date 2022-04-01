package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.BillEntity;
import com.vcc.apigrocerystore.model.request.BillDetailRegistrationFormRequest;

import java.util.List;

public interface BillDAO {

    void createByParam(BillEntity entity) throws Exception;

    void create(long idCustomer, long idUser, long date, List<BillDetailRegistrationFormRequest> billDetails) throws Exception;
}
