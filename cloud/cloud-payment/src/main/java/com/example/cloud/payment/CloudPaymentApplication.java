package com.example.cloud.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 支付服务,提供 订单支付，退单API
 * @author jintaoZou
 * @date 2022/5/13-16:28
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPaymentApplication.class,args);
    }

}
