package com.vcc.apigrocerystore.policies.impl;

import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillDetailFormRequest;
import com.vcc.apigrocerystore.policies.AbstractRule;
import com.vcc.apigrocerystore.policies.BaseRule;
import org.springframework.stereotype.Component;

@Component("BillDetailFormCreateRule")
public class BillDetailFormCreateRule extends AbstractRule<BillDetailFormRequest> implements BaseRule<BillDetailFormRequest> {
    @Override
    public void verify(BillDetailFormRequest form) throws Exception {
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
    }
}
