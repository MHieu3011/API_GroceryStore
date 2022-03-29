package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.ItemDAO;
import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            eLogger.error("Error insert item: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
