package com.vcc.apigrocerystore.controller;

import com.vcc.apigrocerystore.model.request.UserRegistrationForm;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(
            @RequestBody @Valid UserRegistrationForm userRegistrationForm,
            HttpServletRequest request
    ) {
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String strResponse;
        Object serverResponse;

        try {
            serverResponse = userService.createUser(userRegistrationForm);

            strResponse = gson.toJson(serverResponse);
            requestLogger.info("Finish Usercontroller.create {}", requestUri);
            return new ResponseEntity<>(strResponse, HttpStatus.OK);
        } catch (Exception e) {
            eLogger.error("UserController.create error: {}", e.getMessage());
        }
        strResponse = gson.toJson(new Object());
        return new ResponseEntity<>(strResponse, HttpStatus.OK);
    }
}
