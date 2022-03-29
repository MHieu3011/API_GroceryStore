package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.dao.BillDetailDAO;
import com.vcc.apigrocerystore.entities.BillDetailEntity;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillDetailFormRequest;
import com.vcc.apigrocerystore.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BillDetailServiceImpl extends AbstractService implements BillDetailService {

    @Autowired
    private BillDetailDAO billDetailDAO;

    @Override
    public Response create(BillDetailFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        long idBill = form.getIdBill();
        long idItem = form.getIdItem();
        int number = form.getNumber();
        if (idBill < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "idBill must smaller 0");
        }
        if (idItem < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "idItem must smaller 0");
        }
        if (number < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "number must smaller 0");
        }

        BillDetailEntity entity = new BillDetailEntity();
        entity.setIdBill(idBill);
        entity.setIdItem(idItem);
        entity.setNumber(number);
        billDetailDAO.create(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create bill detail successfully")
                .build();
    }
}
