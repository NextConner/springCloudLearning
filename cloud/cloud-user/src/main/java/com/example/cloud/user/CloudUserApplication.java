package com.example.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jintaoZou
 * @date 2022/5/7-11:40
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages="com.example.cloud")
public class CloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUserApplication.class,args);
    }

}
