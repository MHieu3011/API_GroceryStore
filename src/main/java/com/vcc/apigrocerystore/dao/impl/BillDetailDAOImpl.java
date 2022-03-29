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
            String sql1 = "UPDATE storehouse s SET s.number = s.number - ? WHERE s.iditem = ? AND s.number >= ? LIMIT 1";
            statement = connection.prepareStatement(sql1);
            statement.setInt(1, entity.getNumber());
            statement.setLong(2, entity.getIdItem());
            statement.setInt(3, entity.getNumber());
            int check = statement.executeUpdate();
            if (check > 0) {
                String sql = "INSERT INTO billdetail(idbill, iditem, number) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, entity.getIdBill());
                statement.setLong(2, entity.getIdItem());
                statement.setInt(3, entity.getNumber());
                statement.executeUpdate();
                connection.commit();
            } else {
                eLogger.error("Error insert billDetail: There are no more items with id = {} in store house", entity.getIdItem());
            }
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            eLogger.error("Error insert billDetail: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
