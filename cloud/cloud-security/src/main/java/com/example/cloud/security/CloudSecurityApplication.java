package com.example.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jintaoZou
 * @date 2022/5/9-16:13
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSecurityApplication.class,args);
    }

}
