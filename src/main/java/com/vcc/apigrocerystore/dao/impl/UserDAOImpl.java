package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.UserDAO;
import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {
    @Override
    public void create(UserEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO user(username, fullname, password, role, address) VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setQueryTimeout(1);
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getFullName());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getRole());
            statement.setString(5, entity.getAddress());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            eLogger.error("Error insert user: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
