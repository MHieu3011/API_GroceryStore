package com.vcc.apigrocerystore.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDao {
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");

    public void releaseConnectAndStatement(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                eLogger.error("ERROR closing connection: {}", e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                eLogger.error("ERROR closing statement: {}", e.getMessage());
            }
        }
    }

    protected void releaseResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                eLogger.error("ERROR closing connection: {}", e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                eLogger.error("ERROR closing statement: {}", e.getMessage());
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                eLogger.error("ERROR closing resultSet: {}", e.getMessage());
            }
        }
    }
}
