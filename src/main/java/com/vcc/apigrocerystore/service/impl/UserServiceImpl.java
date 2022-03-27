package com.vcc.apigrocerystore.service.impl;

import com.vcc.apigrocerystore.builder.Response;
import com.vcc.apigrocerystore.dao.UserDAO;
import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.model.request.UserRegistrationForm;
import com.vcc.apigrocerystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Response createUser(UserRegistrationForm form) throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUserName(form.getUserName());
        entity.setFullName(form.getFullName());
        entity.setPassword(form.getPassword());
        entity.setAddress(form.getAddress());
        entity.setRole(0);
        userDAO.create(entity);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Create User successfully")
                .build();
    }
}
