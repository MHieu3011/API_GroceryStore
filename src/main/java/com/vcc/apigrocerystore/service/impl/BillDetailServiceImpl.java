package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.dao.BillDetailDAO;
import com.vcc.apigrocerystore.entities.BillDetailEntity;
import com.vcc.apigrocerystore.model.request.BillDetailFormRequest;
import com.vcc.apigrocerystore.policies.BaseRule;
import com.vcc.apigrocerystore.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BillDetailServiceImpl extends AbstractService implements BillDetailService {

    @Autowired
    private BillDetailDAO billDetailDAO;

    @Autowired
    @Qualifier("BillDetailFormCreateRule")
    private BaseRule<BillDetailFormRequest> billDetailFormCreateRule;

    @Override
    public Response create(BillDetailFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        billDetailFormCreateRule.verify(form);

        BillDetailEntity entity = new BillDetailEntity();
        entity.setIdBill(form.getIdBill());
        entity.setIdItem(form.getIdItem());
        entity.setNumber(form.getNumber());
        billDetailDAO.create(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create bill detail successfully")
                .build();
    }
}
