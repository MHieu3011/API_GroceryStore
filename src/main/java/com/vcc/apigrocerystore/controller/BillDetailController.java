package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.BillDetailFormRequest;
import com.vcc.apigrocerystore.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BillDetailController extends BaseController {

    @Autowired
    private BillDetailService billDetailService;

    //Thêm mới chi tiết hóa đơn
    @PostMapping( value = "/bill_detail",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("id_bill") long idBill,
            @RequestParam("id_item") long idItem,
            @RequestParam("number") int number,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            BillDetailFormRequest form = new BillDetailFormRequest();
            form.setRequestUri(requestUri);
            form.setIdBill(idBill);
            form.setIdItem(idItem);
            form.setNumber(number);
            serverResponse = billDetailService.create(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish BillDetailController.create: {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("BillDetail.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

}
