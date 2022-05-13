package com.example.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author jintaoZou
 * @date 2022/5/7-8:58
 */
@EnableEurekaServer
@SpringBootApplication
public class CloudRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudRegistryApplication.class,args);
    }

}
