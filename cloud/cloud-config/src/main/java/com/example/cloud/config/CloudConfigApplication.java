package com.example.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author jintaoZou
 * @date 2022/5/7-9:06
 */
@EnableConfigServer
@SpringBootApplication
public class CloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class,args);
    }
}
