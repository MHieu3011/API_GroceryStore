package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.CustomerFormRequest;
import com.vcc.apigrocerystore.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    //Thêm mới khách hàng
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("full_name") String fullName,
            @RequestParam("sex") int sex,
            @RequestParam("phone_number") String phoneNumber,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            CustomerFormRequest form = new CustomerFormRequest();
            form.setRequestUri(requestUri);
            form.setFullName(fullName);
            form.setSex(sex);
            form.setPhoneNumber(phoneNumber);

            serverResponse = customerService.create(form);
            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish CustomerController.create {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("CustomerController.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }
}
