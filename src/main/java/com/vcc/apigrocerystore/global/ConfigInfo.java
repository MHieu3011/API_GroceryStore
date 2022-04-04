package com.vcc.apigrocerystore.global;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfigInfo {

    private ConfigInfo() {
    }

    private static Config config = ConfigFactory.parseFile(new File("conf.properties"));
    public static final int SERVICE_PORT = config.getInt("service.port");

    //-----------------------------------------------------------------------------------------------
    public static final String MYSQL_DRIVER_CLASS_NAME = config.getString("mysql.jdbc.driver.class.name");
    public static final String MYSQL_JDBC_URL = config.getString("mysql.jdbc.url");
    public static final String MYSQL_USERNAME = config.getString("mysql.username");
    public static final String MYSQL_PASSWORD = config.getString("mysql.password");
    public static final int MYSQL_MAXIMUM_POOL_SIZE = config.getInt("mysql.maximum.pool.size");
    public static final int MYSQL_MINIMUM_IDLE_SIZE = config.getInt("mysql.minimum.idle.size");



    //---------------------------------------------------------------------------------------
    public static final int RESPONSE_CACHE_INFO_SIZE = config.getInt("response.cache.info.size");
    public static final int RESPONSE_CACHE_INFO_EXPIRE = config.getInt("response.cache.info.expire");

}
