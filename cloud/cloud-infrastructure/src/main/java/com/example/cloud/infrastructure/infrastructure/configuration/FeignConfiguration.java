package com.example.cloud.infrastructure.infrastructure.configuration;

import com.example.cloud.infrastructure.infrastructure.security.CloudClientDetailsService;
import com.example.cloud.infrastructure.infrastructure.security.CloudServiceConfigClient;
import feign.Contract;
import feign.RequestInterceptor;
import feign.jaxrs2.JAXRS2Contract;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import javax.inject.Inject;

/**
 * @author jintaoZou
 * @date 2022/5/7-11:23
 */
@Configuration
@EnableFeignClients(basePackages = {"com.example.cloud"})
public class FeignConfiguration {

    @Inject
    private ClientCredentialsResourceDetails resource;

    /**
     *  设置交互为JAX-RS2方式，实际Feign中的JAX-RS2指的是1.1
     *  这样才会提取 JAX-RS 的注解信息作为feignClient 的元数据使用
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new JAXRS2Contract();
    }

    /**
     * 请求时自动加入基于OAuth2的客户端模式认证的Header
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource);
    }


}
