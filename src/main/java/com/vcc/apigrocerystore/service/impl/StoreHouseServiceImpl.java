package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.cache.local.ResponseLocalCache;
import com.vcc.apigrocerystore.dao.ItemDAO;
import com.vcc.apigrocerystore.dao.StoreHouseDAO;
import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.global.ErrorCode;
import com.vcc.apigrocerystore.model.request.StoreHouseFormRequest;
import com.vcc.apigrocerystore.model.response.InfoItemBestSellerResponse;
import com.vcc.apigrocerystore.model.response.InfoItemByExpireResponse;
import com.vcc.apigrocerystore.model.response.InfoItemInStoreHouse;
import com.vcc.apigrocerystore.process.ItemInStoreHouseProcess;
import com.vcc.apigrocerystore.service.StoreHouseService;
import com.vcc.apigrocerystore.utils.CommonUtils;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class StoreHouseServiceImpl extends AbstractService implements StoreHouseService {

    @Autowired
    private StoreHouseDAO storeHouseDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    @Qualifier("responseLocalCache")
    private ResponseLocalCache responseLocalCache;

    @Autowired
    private ItemInStoreHouseProcess process;

    @Override
    public Response create(StoreHouseFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        long idItem = form.getIdItem();
        String codeItem = form.getCodeItem();
        int number = form.getNumber();
        String strDate = form.getDate();
        if (CommonUtils.checkEmpty(codeItem)) {
            throw new CommonException(ErrorCode.CODE_ITEM_MUST_NOT_EMPTY, "code item must not empty");
        }
        if (CommonUtils.checkEmpty(strDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "date must not empty");
        }
        if (number < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "number must smaller 0");
        }
        if (!itemDAO.checkItemByIdAndCode(idItem, codeItem)) {
            throw new CommonException(ErrorCode.ID_INVALID, "item invalid");
        }
        try {
            DateTime d = DateTimeFormat.forPattern(DateTimeUtils.DEFAULT_DATE_FORMAT).parseDateTime(strDate);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.DATE_TIME_INVALID, "Date time invalid");
        }

        StoreHouseEntity entity = new StoreHouseEntity();
        long date = DateTimeUtils.getTimeInSecs(strDate);
        entity.setIdItem(idItem);
        entity.setCodeItem(codeItem);
        entity.setNumber(number);
        entity.setDate(date);
        InfoItemInStoreHouse result = storeHouseDAO.create(entity);

        if (result.getDate() == null) {
            return new Response.Builder(0, HttpStatus.OK.value())
                    .buildMessage("Create item in store house error")
                    .build();
        } else {
            return new Response.Builder(1, HttpStatus.OK.value())
                    .buildMessage("Create item in store house successfully")
                    .buildData(result)
                    .build();
        }
    }

    @Override
    public Response findItemBestSeller(StoreHouseFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        String strFromDate = form.getFromDate();
        String strToDate = form.getToDate();
        String keyword = form.getKeyword();
        int limit = form.getLimit();
        if (CommonUtils.checkEmpty(strFromDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "from date must not empty");
        }
        if (CommonUtils.checkEmpty(strToDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "to date must not empty");
        }
        if (CommonUtils.checkEmpty(keyword)) {
            throw new CommonException(ErrorCode.KEYWORD_MUST_NOT_EMPTY, "keyword must not empty");
        }
        if (!keyword.equalsIgnoreCase("DESC") && !keyword.equalsIgnoreCase("ASC")) {
            throw new CommonException(ErrorCode.KEYWORD_MUST_NOT_EMPTY, "keyword is DESC or ASC");
        }
        if (limit < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "limit must smaller 0");
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

        //gọi cache lấy dữ liệu, nếu có thì trả về cho client
        String key = form.getRequestUri();
        List<InfoItemBestSellerResponse> resultList = (List<InfoItemBestSellerResponse>) responseLocalCache.get(key);
        if (resultList == null) {
            //nếu cache không có thì gọi dao để lấy dữ liệu rồi put cache
            long fromDate = DateTimeUtils.getTimeInSecs(strFromDate);
            long toDate = DateTimeUtils.getTimeInSecs(strToDate);
            resultList = storeHouseDAO.findItemBestSeller(fromDate, toDate, keyword, limit);

            responseLocalCache.put(key, resultList);
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(resultList)
                .build();
    }

    @Override
    public Response findItemByExpire(StoreHouseFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        String strFromDate = form.getFromDate();
        String strToDate = form.getToDate();
        if (CommonUtils.checkEmpty(strFromDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "from date must not empty");
        }
        if (CommonUtils.checkEmpty(strToDate)) {
            throw new CommonException(ErrorCode.DATE_TIME_MUST_NOT_EMPTY, "to date must not empty");
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

        //gọi cache lấy dữ liệu, nếu có thì trả về cho client
        String key = form.getRequestUri();
        List<InfoItemByExpireResponse> resultList = (List<InfoItemByExpireResponse>) responseLocalCache.get(key);
        if (resultList == null) {
            //nếu cache không có thì gọi dao để lấy dữ liệu rồi put cache
            long fromDate = DateTimeUtils.getTimeInSecs(strFromDate);
            long toDate = DateTimeUtils.getTimeInSecs(strToDate);
            resultList = storeHouseDAO.findItemByExpire(fromDate, toDate);

            responseLocalCache.put(key, resultList);
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(resultList)
                .build();
    }

    @Override
    public Response findItemByExpireInputNoDate(StoreHouseFormRequest form) throws Exception {
        //validate dữ liệu đầu vào
        int limit = form.getLimit();
        if (limit < 0) {
            throw new CommonException(ErrorCode.NUMBER_MUST_SMALLER_0, "limit must smaller 0");
        }


        //gọi cache để lấy dữ liệu, nếu cache có thì trả về cho client
        String key = form.getRequestUri();
        List<InfoItemByExpireResponse> resultList = (List<InfoItemByExpireResponse>) responseLocalCache.get(key);
        if (resultList == null) {
            //nếu cache không có thì gọi dao để lấy dữ liệu rồi put cache
            resultList = storeHouseDAO.findItemByExpire(limit);

            responseLocalCache.put(key, resultList);
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(resultList)
                .build();
    }

    @Override
    public Response findItemInStoreHouseMultiProcess() throws Exception {
        CompletableFuture<List<ItemEntity>> itemFuture = process.findAllItem();
        CompletableFuture<List<StoreHouseEntity>> storeHouseFuture = process.findAllStoreHouse();

        CompletableFuture.allOf(itemFuture, storeHouseFuture);

        List<StoreHouseEntity> storeHouseList = storeHouseFuture.get();
        List<ItemEntity> itemList = itemFuture.get();
        List<InfoItemInStoreHouse> resultList = new ArrayList<>();

        for (ItemEntity item : itemList) {
            InfoItemInStoreHouse info = new InfoItemInStoreHouse();
            int number = 0;
            for (StoreHouseEntity storeHouse : storeHouseList){
                if (item.getId() == storeHouse.getIdItem()){
                    number += storeHouse.getNumber();
                }
            }
            info.setName(item.getName());
            info.setNumber(number);
            resultList.add(info);
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(resultList)
                .build();
    }
}
