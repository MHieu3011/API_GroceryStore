package com.vcc.apigrocerystore.dao;

import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.model.response.InfoUserResponse;

import java.util.List;

public interface UserDAO {
    InfoUserResponse create(UserEntity entity) throws Exception;

    InfoUserResponse login(String username, String password, int status) throws Exception;

    void delete(String username) throws Exception;

    boolean checkUserById(long id) throws Exception;

    InfoUserResponse update(UserEntity entity) throws Exception;

    List<InfoUserResponse> findAll() throws Exception;

    List<InfoUserResponse> searchByUsername(String key) throws Exception;
}
