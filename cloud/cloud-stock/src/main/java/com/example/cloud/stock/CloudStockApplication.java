package com.example.cloud.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jintaoZou
 * @date 2022/5/13-16:15
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStockApplication.class,args);
    }

}
