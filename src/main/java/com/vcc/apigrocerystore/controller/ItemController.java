package com.vcc.apigrocerystore.controller;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.ItemFormRequest;
import com.vcc.apigrocerystore.service.ItemService;
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
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    private static final String ERROR_OCCURRED = "an error occurred";

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
            requestLogger.info("Finish ItemController.create {}", requestUri);
            return new ResponseEntity<>(strResponse, HttpStatus.OK);
        } catch (Exception e) {
            eLogger.error("ItemController.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

}
