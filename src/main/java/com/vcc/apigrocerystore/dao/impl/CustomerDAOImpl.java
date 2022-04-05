package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.CustomerDAO;
import com.vcc.apigrocerystore.entities.CustomerEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO {
    @Override
    public void create(CustomerEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO customer(fullname, sex, phonenumber) VALUES(?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getFullName());
            statement.setInt(2, entity.getSex());
            statement.setString(3, entity.getPhoneNumber());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            eLogger.error("Error CustomerDAO.insert customer: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
