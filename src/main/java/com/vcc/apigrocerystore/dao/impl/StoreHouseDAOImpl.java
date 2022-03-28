package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.StoreHouseDAO;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class StoreHouseDAOImpl extends AbstractDAO implements StoreHouseDAO {
    @Override
    public void create(StoreHouseEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO storehouse(iditem, codeitem, number, date) VALUES(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getIdItem());
            statement.setString(2, entity.getCodeItem());
            statement.setInt(3, entity.getNumber());
            statement.setLong(4, entity.getDate());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            eLogger.error("Error insert store house: {}", e.getMessage());
            connection.rollback();
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
