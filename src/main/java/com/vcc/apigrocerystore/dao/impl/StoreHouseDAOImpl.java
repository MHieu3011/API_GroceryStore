package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.StoreHouseDAO;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            eLogger.error("Error StoreHouseDAO.insert store house: {}", e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }

    @Override
    public List<StoreHouseEntity> findItemBestSeller(long fromDate, long toDate, String keyword, int limit) throws Exception {
        List<StoreHouseEntity> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            String sql = "SELECT codeitem, sum(number) numbers FROM storehouse WHERE date BETWEEN ? AND ? GROUP BY codeitem ORDER BY numbers " + keyword + " LIMIT ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, fromDate);
            statement.setLong(2, toDate);
//            statement.setString(3, keyword);
            statement.setInt(3, limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StoreHouseEntity result = new StoreHouseEntity();
                result.setCodeItem(resultSet.getString("codeitem"));
                result.setNumber(resultSet.getInt("numbers"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error StoreHouseDAO.findItemBestSeller: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }
}
