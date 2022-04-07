package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.UserDAO;
import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import com.vcc.apigrocerystore.model.response.InfoUserResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {
    @Override
    public InfoUserResponse create(UserEntity entity) throws Exception {
        InfoUserResponse result = new InfoUserResponse();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO user(username, fullname, sex, password, role, address, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setQueryTimeout(1);
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getFullName());
            statement.setInt(3, entity.getSex());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getRole());
            statement.setString(6, entity.getAddress());
            statement.setInt(7, entity.getStatus());
            statement.executeUpdate();
            connection.commit();
            result.setUsername(entity.getUserName());
            result.setFullName(entity.getFullName());
            result.setAddress(entity.getAddress());
            result.setSex(entity.getSex());
        } catch (Exception e) {
            eLogger.error("Error UserDAO.insert user: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
        return result;
    }

    @Override
    public InfoUserResponse login(String username, String password, int status) throws Exception {
        InfoUserResponse result = new InfoUserResponse();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            StringBuilder sql = new StringBuilder("SELECT username, fullname, sex, address FROM user");
            sql.append(" WHERE username = ? AND password = ? AND status = ?");
            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, status);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.setUsername(resultSet.getString("username"));
                result.setFullName(resultSet.getString("fullname"));
                result.setSex(resultSet.getInt("sex"));
                result.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            eLogger.error("Error UserDAO.login: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public void delete(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "UPDATE user u SET u.status = 0 WHERE u.username = ? AND u.id != 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            eLogger.error("Error UserDAO.delete: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }

    @Override
    public boolean checkUserById(long id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT fullname FROM user WHERE id = ?";
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
            eLogger.error("Error UserDAO.checkUserById: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return true;
    }
}
