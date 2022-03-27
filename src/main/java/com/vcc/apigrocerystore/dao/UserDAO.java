package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.UserEntity;

public interface UserDAO {
    void create(UserEntity entity) throws Exception;
}
