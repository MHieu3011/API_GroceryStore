package com.vcc.apigrocerystore.factory;

import com.vcc.apigrocerystore.global.ConfigInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionFactory {

    private static MySQLConnectionFactory ourInstance;

    public static MySQLConnectionFactory getInstance() {
        if (ourInstance == null) {
            synchronized (MySQLConnectionFactory.class) {
                if (ourInstance == null) {
                    ourInstance = new MySQLConnectionFactory();
                }
            }
        }
        return ourInstance;
    }

    private static HikariDataSource hikariDataSource;

    private MySQLConnectionFactory() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("Hikari-MySQL-Pool");
        config.setDriverClassName(ConfigInfo.MYSQL_DRIVER_CLASS_NAME);
        config.setJdbcUrl(ConfigInfo.MYSQL_JDBC_URL);
        config.setUsername(ConfigInfo.MYSQL_USERNAME);
        config.setPassword(ConfigInfo.MYSQL_PASSWORD);
        config.setMaximumPoolSize(ConfigInfo.MYSQL_MAXIMUM_POOL_SIZE);
        config.setMinimumIdle(ConfigInfo.MYSQL_MINIMUM_IDLE_SIZE);
        config.setAutoCommit(true);

        hikariDataSource = new HikariDataSource(config);
    }

    public Connection getMySQLConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
