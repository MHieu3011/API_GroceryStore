package com.vcc.apigrocerystore.policies.impl;

import com.vcc.apigrocerystore.dao.CustomerDAO;
import com.vcc.apigrocerystore.dao.UserDAO;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillDetailRegistrationFormRequest;
import com.vcc.apigrocerystore.model.request.BillRegistrationFormRequest;
import com.vcc.apigrocerystore.policies.AbstractRule;
import com.vcc.apigrocerystore.policies.BaseRule;
import com.vcc.apigrocerystore.utils.CommonUtils;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("billRegistrationFormCreateRule")
public class BillRegistrationFormCreateRule extends AbstractRule<BillRegistrationFormRequest> implements BaseRule<BillRegistrationFormRequest> {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void verify(BillRegistrationFormRequest form) throws Exception {
        long idCustomer = form.getIdCustomer();
        long idUser = form.getIdUser();
        String strDate = form.getDate();
        List<BillDetailRegistrationFormRequest> billDetails = form.getBillDetails();
        if (idCustomer <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id Customer invalid");
        }
        if (!customerDAO.checkCustomerById(idCustomer)) {
            throw new CommonException(ErrorCode.ID_INVALID, "No Customer invalid");
        }
        if (idUser <= 0) {
            throw new CommonException(ErrorCode.ID_INVALID, "id User invalid");
        }
        if (!userDAO.checkUserById(idUser)) {
            throw new CommonException(ErrorCode.ID_INVALID, "No User invalid");
        }
        if (CommonUtils.checkEmpty(strDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
        try {
            DateTime d = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strDate);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.DATE_TIME_INVALID, "date format invalid");
        }
        if (billDetails.isEmpty()) {
            throw new CommonException(ErrorCode.BILL_DETAIL_FORM_REQUEST_NOT_EMPTY, "bill detail form request not empty");
        }
    }
}
