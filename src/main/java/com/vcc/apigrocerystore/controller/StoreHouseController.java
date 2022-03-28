package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.StoreHouseFormRequest;
import com.vcc.apigrocerystore.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/storehouse")
public class StoreHouseController extends BaseController {

    @Autowired
    private StoreHouseService storeHouseService;

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
}
