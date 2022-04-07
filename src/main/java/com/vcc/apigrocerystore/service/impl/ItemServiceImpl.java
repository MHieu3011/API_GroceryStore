package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.adapter.EntityAdapter;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.cache.local.ResponseLocalCache;
import com.vcc.apigrocerystore.dao.ItemDAO;
import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.ItemFormRequest;
import com.vcc.apigrocerystore.model.response.InfoItemResponse;
import com.vcc.apigrocerystore.service.ItemService;
import com.vcc.apigrocerystore.utils.CommonUtils;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends AbstractService implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private EntityAdapter<ItemEntity, InfoItemResponse> itemAdapter;

    @Autowired
    @Qualifier("responseLocalCache")
    private ResponseLocalCache responseLocalCache;

    @Override
    public Response create(ItemFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        String code = form.getCode();
        String name = form.getName();
        String strFromDate = form.getFromDate();
        String strToDate = form.getToDate();
        long price = form.getPrice();
        String brand = form.getBrand();
        if (CommonUtils.checkEmpty(code)) {
            throw new CommonException(ErrorCode.ITEM_CODE_MUST_NOT_EMPTY, "item code must not empty");
        }
        if (CommonUtils.checkEmpty(strFromDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "from date must not empty");
        }
        if (CommonUtils.checkEmpty(strToDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "to date must not empty");
        }
        if (price <= 0) {
            throw new CommonException(ErrorCode.ITEM_PRICE_MUST_LONG, "price must data type long");
        }
        if (CommonUtils.checkEmpty(brand)) {
            throw new CommonException(ErrorCode.ITEM_BRAND_MUST_NOT_EMPTY, "brand must not empty");
        }
        DateTime d1, d2;
        try {
            d1 = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strFromDate);
            d2 = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strToDate);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.DATE_TIME_INVALID, "date format invalid");
        }
        if (d1.compareTo(d2) > 0) {
            throw new CommonException(ErrorCode.DATE_TIME_INVALID, "From date dont after to date");
        }

        ItemEntity entity = new ItemEntity();
        long fromDate = DateTimeUtils.getTimeInSecs(form.getFromDate());
        long toDate = DateTimeUtils.getTimeInSecs(form.getToDate());
        entity.setCode(code);
        entity.setName(name);
        entity.setFromDate(fromDate);
        entity.setToDate(toDate);
        entity.setPrice(price);
        entity.setBrand(brand);
        itemDAO.create(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create item successfully")
                .build();
    }

    @Override
    public Response findAll(ItemFormRequest form) throws Exception {

        //gọi cache, nếu cache có trả về client
        String key = form.getRequestUri();
        List<InfoItemResponse> resultList = (List<InfoItemResponse>) responseLocalCache.get(key);
        if (resultList == null) {
            //nếu cache không có thì lấy dữ liệu từ DAO và put cache
            resultList = itemDAO.findAll().stream()
                    .map(entity -> itemAdapter.transform(entity))
                    .collect(Collectors.toList());

            responseLocalCache.put(key, resultList);
        }


        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(resultList)
                .build();
    }
}
