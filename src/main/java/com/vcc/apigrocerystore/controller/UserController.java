package com.vcc.apigrocerystore.controller;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.UserFormRequest;
import com.vcc.apigrocerystore.model.request.UserRegistrationFormRequest;
import com.vcc.apigrocerystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    //Thêm mới nhân viên bằng các param
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("username") String userName,
            @RequestParam("full_name") String fullName,
            @RequestParam("sex") int sex,
            @RequestParam("password") String password,
            @RequestParam("address") String address,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;

        try {
            UserFormRequest form = new UserFormRequest();
            form.setRequestUri(requestUri);
            form.setUserName(userName);
            form.setFullName(fullName);
            form.setSex(sex);
            form.setPassword(password);
            form.setAddress(address);
            serverResponse = userService.create(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish UserController.create {}", requestUri);
        } catch (Exception e) {
            eLogger.error("UserController.create error: {} in {}", e.getMessage(), stopWatch.stop());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //Thêm mới nhân viên bằng các body
    @PostMapping(value = "/body", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createByRequestBody(
            @RequestBody @Valid UserRegistrationFormRequest userRegistrationFormRequest,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;

        try {
            userRegistrationFormRequest.setRequestUri(requestUri);
            serverResponse = userService.createByRequestBody(userRegistrationFormRequest);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish UserController.create {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("UserController.create error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //Login
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> login(
            @RequestParam("user_name") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            UserFormRequest form = new UserFormRequest();
            form.setRequestUri(requestUri);
            form.setUserName(userName);
            form.setPassword(password);
            serverResponse = userService.login(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish UserController.login {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("UserController.login error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }

    //    Cho thôi việc nhân viên
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(
            @RequestParam("user_name") String userName,
            HttpServletRequest request
    ) {
        StopWatch stopWatch = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Response serverResponse;
        try {
            UserFormRequest form = new UserFormRequest();
            form.setUserName(userName);
            serverResponse = userService.delete(form);

            strResponse = gson.toJson(serverResponse, Response.class);
            requestLogger.info("Finish UserController.delete {} in {}", requestUri, stopWatch.stop());
        } catch (Exception e) {
            eLogger.error("UserController.delete error: {}", e.getMessage());
            strResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_OCCURRED);
        }
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }
}
