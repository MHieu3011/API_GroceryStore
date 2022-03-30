package com.vcc.apigrocerystore.dao.impl;

import com.vcc.apigrocerystore.dao.BillDAO;
import com.vcc.apigrocerystore.entities.BillEntity;
import com.vcc.apigrocerystore.factory.MySQLConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class BillDAOImpl extends AbstractDAO implements BillDAO {

    @Override
    public void create(BillEntity entity) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnectionFactory.getInstance().getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO bill(idcustomer, iduser, date, totalmoney) VALUES(?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getIdCustomer());
            statement.setLong(2, entity.getIdUser());
            statement.setLong(3, entity.getDate());
            statement.setLong(4, entity.getTotalMoney());
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            eLogger.error("Error BillDAO.insert bill: {}", e.getMessage());
        } finally {
            releaseConnectAndStatement(connection, statement);
        }
    }
}
