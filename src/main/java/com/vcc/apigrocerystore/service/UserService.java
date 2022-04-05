package com.vcc.apigrocerystore.service;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.model.request.UserFormRequest;
import com.vcc.apigrocerystore.model.request.UserRegistrationFormRequest;

public interface UserService {
    Response create(UserFormRequest form) throws Exception;

    Response createByRequestBody(UserRegistrationFormRequest form) throws Exception;

    Response login(UserFormRequest form) throws Exception;

    Response delete(UserFormRequest form) throws Exception;
}
