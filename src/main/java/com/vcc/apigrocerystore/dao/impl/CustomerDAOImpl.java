package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.CustomerDAO;
import com.vcc.apigrocerystore.entities.CustomerEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import com.vcc.apigrocerystore.model.response.InfoCustomerResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO {
    @Override
    public InfoCustomerResponse create(CustomerEntity entity) throws Exception {
        InfoCustomerResponse result = new InfoCustomerResponse();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO customer(fullname, sex, phonenumber) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getFullName());
            statement.setInt(2, entity.getSex());
            statement.setString(3, entity.getPhoneNumber());
            statement.executeUpdate();
            connection.commit();
            result.setFullName(entity.getFullName());
            result.setSex(entity.getSex());
            result.setPhoneNumber(entity.getPhoneNumber());
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            eLogger.error("Error CustomerDAO.insert customer: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
        return result;
    }

    @Override
    public boolean checkCustomerById(long id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT fullname FROM customer WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            String fullName = null;
            while (resultSet.next()) {
                fullName = resultSet.getString("fullname");
            }
            if (fullName == null)
                return false;
        } catch (Exception e) {
            eLogger.error("Error CustomerDAO.checkCustomerById: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return true;
    }
}
