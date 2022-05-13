package com.example.cloud.infrastructure.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jintaoZou
 * @date 2022/5/7-14:04
 */
@EntityScan(basePackages = {"com.example.cloud"})
@Configuration
public class JPAConfiguration {
}
