package com.example.cloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author jintaoZou
 * @date 2022/5/10-15:21
 */
@RefreshScope
@EnableAuthorizationServer
@Configuration
public class CloudAuthorizationServerConfigAdapter extends AuthorizationServerConfigurerAdapter {

    @Value("${token.key.access:permitAll()}")
    private String tokenKeyAccess;

    @Value("${token.key.check.access:permitAll()}")
    private String tokenKeyCheckAccess;



    @Autowired
    CloudClientDetailsService cloudClientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(cloudClientDetailsService);
    }

    /**
     * 防止访问 /oauth/token 换取token 时401 , 未配置允许所有请求
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().tokenKeyAccess(tokenKeyAccess).checkTokenAccess(tokenKeyCheckAccess);
    }

}
