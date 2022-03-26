package com.vcc.apigrocerystore;

import com.vcc.apigrocerystore.global.ConfigInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

@SpringBootApplication
public class ApiGroceryStoreApplication {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("server.port", ConfigInfo.SERVICE_PORT);

        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder()
                .sources(ApiGroceryStoreApplication.class)
                .properties(prop);

        applicationBuilder.run(args);
    }

}
