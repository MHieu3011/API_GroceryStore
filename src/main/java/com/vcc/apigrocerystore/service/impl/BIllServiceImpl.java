package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.dao.BillDAO;
import com.vcc.apigrocerystore.entities.BillEntity;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillFormRequest;
import com.vcc.apigrocerystore.service.BillService;
import com.vcc.apigrocerystore.utils.CommonUtils;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BIllServiceImpl extends AbstractService implements BillService {

    @Autowired
    private BillDAO billDAO;

    @Override
    public Response create(BillFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        long idCustomer = form.getIdCustomer();
        long idUser = form.getIdUser();
        String strDate = form.getDate();
        long totalMoney = form.getTotalMoney();
        if (CommonUtils.checkEmpty(strDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }

        BillEntity entity = new BillEntity();
        long date = DateTimeUtils.getTimeInSecs(strDate);
        entity.setIdCustomer(idCustomer);
        entity.setIdUser(idUser);
        entity.setDate(date);
        entity.setTotalMoney(totalMoney);
        billDAO.create(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create bill successfully")
                .build();
    }
}
