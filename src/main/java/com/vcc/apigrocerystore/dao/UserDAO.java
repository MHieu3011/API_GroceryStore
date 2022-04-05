package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.model.response.InfoUserResponse;

public interface UserDAO {
    InfoUserResponse create(UserEntity entity) throws Exception;

    InfoUserResponse login(String username, String password, int status) throws Exception;
}
