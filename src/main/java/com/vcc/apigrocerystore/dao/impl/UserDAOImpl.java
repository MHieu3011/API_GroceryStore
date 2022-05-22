package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.UserDAO;
import com.vcc.apigrocerystore.entities.UserEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import com.vcc.apigrocerystore.model.response.InfoUserResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            StringBuilder sql = new StringBuilder("SELECT id, username, fullname, sex, address, role FROM user");
            sql.append(" WHERE username = ? AND password = ? AND status = ?");
            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, status);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setUsername(resultSet.getString("username"));
                result.setFullName(resultSet.getString("fullname"));
                result.setSex(resultSet.getInt("sex"));
                result.setAddress(resultSet.getString("address"));
                result.setRole(resultSet.getInt("role"));
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

    @Override
    public InfoUserResponse update(UserEntity entity) throws Exception {
        InfoUserResponse result = new InfoUserResponse();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "UPDATE user SET username = ?, fullname = ?, sex = ?, password = ?, address = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setQueryTimeout(1);
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getFullName());
            statement.setInt(3, entity.getSex());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getAddress());
            statement.setLong(6, entity.getId());
            statement.executeUpdate();
            connection.commit();
            result.setUsername(entity.getUserName());
            result.setFullName(entity.getFullName());
            result.setAddress(entity.getAddress());
            result.setSex(entity.getSex());
        } catch (Exception e) {
            eLogger.error("Error UserDAO.update user: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
        return result;
    }

    @Override
    public List<InfoUserResponse> findAll() throws Exception {
        List<InfoUserResponse> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT id, username, fullname, sex, address FROM user WHERE status = 1";
            statement = connection.prepareStatement(sql);
            statement.setQueryTimeout(1);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InfoUserResponse result = new InfoUserResponse();
                result.setId(resultSet.getInt("id"));
                result.setUsername(resultSet.getString("username"));
                result.setFullName(resultSet.getString("fullname"));
                result.setSex(resultSet.getInt("sex"));
                result.setAddress(resultSet.getString("address"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error UserDAO.findAll user: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }

    @Override
    public List<InfoUserResponse> searchByUsername(String key) throws Exception {
        List<InfoUserResponse> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT id, username, fullname, sex, address FROM user WHERE status = 1 AND username LIKE '%" + key + "%'";
            statement = connection.prepareStatement(sql);
            statement.setQueryTimeout(1);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InfoUserResponse result = new InfoUserResponse();
                result.setId(resultSet.getInt("id"));
                result.setUsername(resultSet.getString("username"));
                result.setFullName(resultSet.getString("fullname"));
                result.setSex(resultSet.getInt("sex"));
                result.setAddress(resultSet.getString("address"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error UserDAO.searchByUsername user: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }
}
