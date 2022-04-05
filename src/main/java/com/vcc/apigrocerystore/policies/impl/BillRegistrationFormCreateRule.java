package com.vcc.apigrocerystore.policies.impl;

import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillDetailRegistrationFormRequest;
import com.vcc.apigrocerystore.model.request.BillRegistrationFormRequest;
import com.vcc.apigrocerystore.policies.AbstractRule;
import com.vcc.apigrocerystore.policies.BaseRule;
import com.vcc.apigrocerystore.utils.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("billRegistrationFormCreateRule")
public class BillRegistrationFormCreateRule extends AbstractRule<BillRegistrationFormRequest> implements BaseRule<BillRegistrationFormRequest> {
    @Override
    public void verify(BillRegistrationFormRequest form) throws Exception {
        long idCustomer = form.getIdCustomer();
        long idUser = form.getIdUser();
        String strDate = form.getDate();
        List<BillDetailRegistrationFormRequest> billDetails = form.getBillDetails();
        if (idCustomer <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id Customer invalid");
        }
        if (idUser <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id User invalid");
        }
        if (CommonUtils.checkEmpty(strDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
        if (billDetails.isEmpty()) {
            throw new CommonException(ErrorCode.BILL_DETAIL_FORM_REQUEST_NOT_EMPTY, "bill detail form request not empty");
        }
    }
}
