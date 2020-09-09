package com.javaxiu.dbsharding.simple;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = SpringBootConfiguration.class)
@SpringBootApplication
public class ShardingJdbcSimpleBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcSimpleBootstrap.class, args);
    }

}
