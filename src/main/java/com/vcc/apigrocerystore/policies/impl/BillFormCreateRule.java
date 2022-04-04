package com.vcc.apigrocerystore.policies.impl;

import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillFormRequest;
import com.vcc.apigrocerystore.policies.AbstractRule;
import com.vcc.apigrocerystore.policies.BaseRule;
import com.vcc.apigrocerystore.utils.CommonUtils;
import org.springframework.stereotype.Component;

@Component("BillFormCreateRule")
public class BillFormCreateRule extends AbstractRule<BillFormRequest> implements BaseRule<BillFormRequest> {
    @Override
    public void verify(BillFormRequest form) throws Exception {
        long idCustomer = form.getIdCustomer();
        long idUser = form.getIdUser();
        String strDate = form.getDate();
        long totalMoney = form.getTotalMoney();
        if (idCustomer <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id Customer invalid");
        }
        if (idUser <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id User invalid");
        }
        if (CommonUtils.checkEmpty(strDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
    }
}
