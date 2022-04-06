package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.cache.local.ResponseLocalCache;
import com.vcc.apigrocerystore.dao.BillDAO;
import com.vcc.apigrocerystore.entities.BillEntity;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.BillDetailRegistrationFormRequest;
import com.vcc.apigrocerystore.model.request.BillFormRequest;
import com.vcc.apigrocerystore.model.request.BillRegistrationFormRequest;
import com.vcc.apigrocerystore.model.response.InfoBillResponse;
import com.vcc.apigrocerystore.model.response.InfoTotalRevenueByBrand;
import com.vcc.apigrocerystore.policies.BaseRule;
import com.vcc.apigrocerystore.service.BillService;
import com.vcc.apigrocerystore.utils.CommonUtils;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BIllServiceImpl extends AbstractService implements BillService {

    @Autowired
    private BillDAO billDAO;

    @Autowired
    @Qualifier("responseLocalCache")
    private ResponseLocalCache responseLocalCache;

    @Autowired
    @Qualifier("BillFormCreateRule")
    private BaseRule<BillFormRequest> billFormCreateRule;

    @Autowired
    @Qualifier("billRegistrationFormCreateRule")
    private BaseRule<BillRegistrationFormRequest> billRegistrationFormCreateRule;

    @Override
    public Response createByParam(BillFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        billFormCreateRule.verify(form);

        BillEntity entity = new BillEntity();
        long date = DateTimeUtils.getTimeInSecs(form.getDate());
        entity.setIdCustomer(form.getIdCustomer());
        entity.setIdUser(form.getIdUser());
        entity.setDate(date);
        entity.setTotalMoney(form.getTotalMoney());
        billDAO.createByParam(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create bill successfully")
                .build();
    }

    @Override
    public Response create(BillRegistrationFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        billRegistrationFormCreateRule.verify(form);

        long idCustomer = form.getIdCustomer();
        long idUser = form.getIdUser();
        String strDate = form.getDate();
        List<BillDetailRegistrationFormRequest> billDetails = form.getBillDetails();
        long date = DateTimeUtils.getTimeInSecs(strDate);
        InfoBillResponse result = billDAO.create(idCustomer, idUser, date, billDetails);

        if (result.getDate() == null) {
            return new Response.Builder(0, HttpStatus.OK.value())
                    .buildMessage("Create bill error")
                    .build();
        } else {
            return new Response.Builder(1, HttpStatus.OK.value())
                    .buildMessage("Create bill successfully")
                    .buildData(result)
                    .build();
        }
    }

    @Override
    public Response getTotalRevenueByBrand(BillFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        String strFromDate = form.getFromDate();
        String strToDate = form.getToDate();
        String brand = form.getBrand();
        if (CommonUtils.checkEmpty(strFromDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
        if (CommonUtils.checkEmpty(strToDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
        if (CommonUtils.checkEmpty(brand)) {
            throw new CommonException(ErrorCode.ITEM_BRAND_MUST_NOT_EMPTY, "brand must not empty");
        }
        DateTime d1 = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strFromDate);
        DateTime d2 = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strToDate);
        if (d1.compareTo(d2) > 0) {
            throw new CommonException(ErrorCode.DATE_TIME_INVALID, "From date dont after to date");
        }


        //gọi cache, nếu cache có trả về client
        String key = form.getRequestUri();
        InfoTotalRevenueByBrand result = (InfoTotalRevenueByBrand) responseLocalCache.get(key);
        if (result == null) {
            //nếu cache không có thì lấy từ DAO và put cache
            long fromDate = DateTimeUtils.getTimeInSecs(strFromDate);
            long toDate = DateTimeUtils.getTimeInSecs(strToDate);
            result = billDAO.getTotalRevenueByBrand(fromDate, toDate, brand);

            responseLocalCache.put(key, result);
        }


        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("message from server")
                .buildData(result)
                .build();
    }
}
