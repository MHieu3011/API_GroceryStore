package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.BillDetailDAO;
import com.vcc.apigrocerystore.entities.BillDetailEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class BillDetailDAOImpl extends AbstractDAO implements BillDetailDAO {

    @Override
    public void create(BillDetailEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO billdetail(idbill, iditem, number) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getIdBill());
            statement.setLong(2, entity.getIdItem());
            statement.setInt(3, entity.getNumber());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            eLogger.error("Error insert billDetail: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
