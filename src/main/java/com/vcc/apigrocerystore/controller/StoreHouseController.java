package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.StoreHouseFormRequest;
import com.vcc.apigrocerystore.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/storehouse")
public class StoreHouseController extends BaseController {

    @Autowired
    private StoreHouseService storeHouseService;

    //Thêm mới lô hàng vào kho
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("id_item") long idItem,
            @RequestParam("code_item") String codeItem,
            @RequestParam("number") int number,
            @RequestParam("date") String date,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            StoreHouseFormRequest form = new StoreHouseFormRequest();
            form.setRequestUri(requestUri);
            form.setIdItem(idItem);
            form.setCodeItem(codeItem);
            form.setNumber(number);
            form.setDate(date);
            serverResponse = storeHouseService.create(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish StoreHouseController.create: {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("StoreHouseController.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //    Các mặt hàng bán chạy nhất (DESC) hoặc kém nhất(ASC) trong tháng
    @GetMapping(value = "/best_seller", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> findItemBestSeller(
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate,
            @RequestParam(value = "keyword", required = false, defaultValue = "DESC") String keyword,
            @RequestParam(value = "limit", required = false, defaultValue = "1") int limit,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            StoreHouseFormRequest form = new StoreHouseFormRequest();
            form.setRequestUri(requestUri);
            form.setFromDate(fromDate);
            form.setToDate(toDate);
            form.setKeyword(keyword);
            form.setLimit(limit);
            serverResponse = storeHouseService.findItemBestSeller(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish StoreHouseController.findItemBestSeller: {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("StoreHouseController.findItemBestSeller error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //Các mặt hàng có hạn sử dụng trong khoảng thời gian và còn hàng trong kho
    @GetMapping(value = "/expire", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> findItemByExpire(
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            StoreHouseFormRequest form = new StoreHouseFormRequest();
            form.setRequestUri(requestUri);
            form.setFromDate(fromDate);
            form.setToDate(toDate);
            serverResponse = storeHouseService.findItemByExpire(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish StoreHouseController.findItemByExpire: {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("StoreHouseController.findItemByExpire error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //    Các mặt hàng có trong kho, đc sắp xếp theo hạn sử dụng để cửa hàng bày bán trước
    @GetMapping(value = "/expire/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> findItemByExpire(
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            StoreHouseFormRequest form = new StoreHouseFormRequest();
            form.setRequestUri(requestUri);
            form.setLimit(limit);
            serverResponse = storeHouseService.findItemByExpireInputNoDate(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish StoreHouseController.findItemByExpire Input no date: {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("StoreHouseController.findItemByExpire Input no date error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }
}
