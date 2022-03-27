package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.UserRegistrationForm;

public interface UserService {
    Response createUser(UserRegistrationForm form) throws Exception;
}
