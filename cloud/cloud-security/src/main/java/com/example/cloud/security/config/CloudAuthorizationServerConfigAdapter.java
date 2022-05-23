package com.example.cloud.security.config;

import com.example.cloud.infrastructure.infrastructure.security.CloudClientDetailsService;
import com.example.cloud.infrastructure.infrastructure.security.JWTAccessTokenService;
import com.example.cloud.infrastructure.infrastructure.security.UsernamePassAuthenticationProvider;
import com.example.cloud.infrastructure.infrastructure.service.CloudUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.inject.Inject;

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
    private AuthenticationManager authenticationManager;

    @Autowired
    private CloudClientDetailsService cloudClientDetailsService;

    @Autowired
    private UsernamePassAuthenticationProvider userAuthenticationProvider;

    @Inject
    private CloudUserDetailService userDetailService;

    @Inject
    private JWTAccessTokenService tokenService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(cloudClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailService)
                .tokenServices(tokenService)
                //控制TokenEndpoint端点请求访问的类型，默认HttpMethod.POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);;
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
