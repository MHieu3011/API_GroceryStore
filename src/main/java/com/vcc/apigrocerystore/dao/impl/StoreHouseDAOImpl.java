package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.StoreHouseDAO;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import com.vcc.apigrocerystore.model.response.InfoItemBestSellerResponse;
import com.vcc.apigrocerystore.model.response.InfoItemByExpireResponse;
import com.vcc.apigrocerystore.utils.DateTimeUtils;
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
    public List<InfoItemBestSellerResponse> findItemBestSeller(long fromDate, long toDate, String keyword, int limit) throws Exception {
        List<InfoItemBestSellerResponse> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            StringBuilder sql = new StringBuilder("SELECT i.name AS name, i.brand AS brand, sum(s.number) AS numbers");
            sql.append(" FROM storehouse s");
            sql.append(" INNER JOIN item i ON s.iditem = i.id AND s.codeitem = i.code");
            sql.append(" WHERE date BETWEEN ? AND ?");
            sql.append(" GROUP BY s.codeitem");
            sql.append(" ORDER BY numbers " + keyword + " LIMIT ?");
            statement = connection.prepareStatement(sql.toString());
            statement.setLong(1, fromDate);
            statement.setLong(2, toDate);
            statement.setInt(3, limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InfoItemBestSellerResponse result = new InfoItemBestSellerResponse();
                result.setName(resultSet.getString("name"));
                result.setBrand(resultSet.getString("brand"));
                result.setNumbers(resultSet.getInt("numbers"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error StoreHouseDAO.findItemBestSeller: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }

    @Override
    public List<InfoItemByExpireResponse> findItemByExpire(long fromDate, long toDate) throws Exception {
        List<InfoItemByExpireResponse> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            StringBuilder sql = new StringBuilder("SELECT i.name, s.number, i.fromdate, i.todate, i.brand");
            sql.append(" FROM item i");
            sql.append(" INNER JOIN storehouse s ON s.iditem=i.id AND s.codeitem=i.code");
            sql.append(" WHERE i.todate BETWEEN ? AND ? AND s.number > 0");
            sql.append(" ORDER BY i.todate");
            statement = connection.prepareStatement(sql.toString());
            statement.setLong(1, fromDate);
            statement.setLong(2, toDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InfoItemByExpireResponse result = new InfoItemByExpireResponse();
                result.setName(resultSet.getString("name"));
                result.setNumber(resultSet.getInt("number"));
                result.setFromDate(DateTimeUtils.formatTimeInSec(resultSet.getLong("fromdate"), DateTimeUtils.DEFAULT_DATE_FORMAT));
                result.setToDate(DateTimeUtils.formatTimeInSec(resultSet.getLong("todate"), DateTimeUtils.DEFAULT_DATE_FORMAT));
                result.setBrand(resultSet.getString("brand"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error StoreHouseDAO.findItemByExpire: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }

    @Override
    public List<InfoItemByExpireResponse> findItemByExpire(int limit) throws Exception {
        List<InfoItemByExpireResponse> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            StringBuilder sql = new StringBuilder("SELECT i.name, s.number, i.fromdate, i.todate, i.brand");
            sql.append(" FROM item i");
            sql.append(" INNER JOIN storehouse s ON s.iditem=i.id AND s.codeitem=i.code");
            sql.append(" WHERE s.number > 0");
            sql.append(" ORDER BY i.todate");
            sql.append(" LIMIT ?");
            statement = connection.prepareStatement(sql.toString());
            statement.setLong(1, limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InfoItemByExpireResponse result = new InfoItemByExpireResponse();
                result.setName(resultSet.getString("name"));
                result.setNumber(resultSet.getInt("number"));
                result.setFromDate(DateTimeUtils.formatTimeInSec(resultSet.getLong("fromdate"), DateTimeUtils.DEFAULT_DATE_FORMAT));
                result.setToDate(DateTimeUtils.formatTimeInSec(resultSet.getLong("todate"), DateTimeUtils.DEFAULT_DATE_FORMAT));
                result.setBrand(resultSet.getString("brand"));
                resultList.add(result);
            }
        } catch (Exception e) {
            eLogger.error("Error StoreHouseDAO.findItemByExpire: {}", e.getMessage());
        } finally {
            releaseResource(connection, statement, resultSet);
        }
        return resultList;
    }
}
