package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.ItemDAO;
import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDAOImpl extends AbstractDAO implements ItemDAO {
    @Override
    public void create(ItemEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO item(code, name, fromdate, todate, price, brand) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getCode());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getFromDate());
            statement.setLong(4, entity.getToDate());
            statement.setLong(5, entity.getPrice());
            statement.setString(6, entity.getBrand());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            eLogger.error("Error ItemDAO.insert item: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }

    @Override
    public List<ItemEntity> findAll() throws Exception {
        List<ItemEntity> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT code, name, fromdate, todate, price, brand FROM item";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ItemEntity result = new ItemEntity();
                result.setCode(resultSet.getString("code"));
                result.setName(resultSet.getString("name"));
                result.setFromDate(resultSet.getLong("fromdate"));
                result.setToDate(resultSet.getLong("todate"));
                result.setPrice(resultSet.getLong("price"));
                result.setBrand(resultSet.getString("brand"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error ItemDAO.findAll item: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }

    @Override
    public boolean checkItemByIdAndCode(long id, String code) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT name FROM item WHERE id = ? AND code = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setString(2, code);
            resultSet = statement.executeQuery();
            String name = null;
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            if (name == null)
                return false;

        } catch (Exception e) {
            eLogger.error("Error ItemDAO.checkItemByIdAndCode: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return true;
    }
}
