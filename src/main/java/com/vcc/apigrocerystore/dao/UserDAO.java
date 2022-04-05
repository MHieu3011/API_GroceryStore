package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.model.response.InfoUser;

public interface UserDAO {
    void create(UserEntity entity) throws Exception;

    InfoUser login(String username, String password, int status) throws Exception;
}
