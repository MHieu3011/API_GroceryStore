package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.exception.CommonException;
import com.vcc.apigrocerystore.model.request.ItemFormRequest;
import com.vcc.apigrocerystore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    //Thêm mới thông tin mặt hàng
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate,
            @RequestParam("price") int price,
            @RequestParam("brand") String brand,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;

        try {
            ItemFormRequest form = new ItemFormRequest();
            form.setRequestUri(requestUri);
            form.setCode(code);
            form.setName(name);
            form.setFromDate(fromDate);
            form.setToDate(toDate);
            form.setPrice(price);
            form.setBrand(brand);
            serverResponse = itemService.create(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish ItemController.create {} in {}", requestUri, stopWatch.stop());
        } catch (CommonException ce) {
            eLogger.error("Controller Error: {}", ce.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ce.getMessage());
        } catch (Exception e) {
            eLogger.error("ItemController.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> findAll(
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {

            ItemFormRequest form = new ItemFormRequest();
            form.setRequestUri(requestUri);
            serverResponse = itemService.findAll(form);
            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish ItemController.findAll {} in {}", requestUri, stopWatch.stop());
        } catch (CommonException ce) {
            eLogger.error("Controller Error: {}", ce.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ce.getMessage());
        } catch (Exception e) {
            eLogger.error("ItemController.findAll error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }
}
