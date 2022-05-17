package com.example.cloud.infrastructure.infrastructure.configuration;

import com.example.cloud.infrastructure.infrastructure.security.JWTAccessTokenService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.inject.Inject;

/**
 * @author jintaoZou
 * @date 2022/5/9-14:02
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Inject
    private JWTAccessTokenService tokenService;

    /**
     * 配置HTTP访问相关的安全选项
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 基于JWT来绑定用户状态，所以服务端可以是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 关闭CSRF（Cross Site Request Forgery）跨站请求伪造的防御
        // 因为需要状态存储CSRF Token才能开启该功能
        http.csrf().disable();
        // 关闭HTTP Header中的X-Frame-Options选项，允许页面在frame标签中打开
        http.headers().frameOptions().disable();
        // 关闭HTTP Header中的Cache-Control:no-cache，允许缓存响应结果
        http.headers().cacheControl().disable();
        // 设置服务的默认安全规则：
        // 在HTTP过滤器层面，在所有的服务都允许未认证的访问
        // 在方法安全层面，每个方法上设置所需要的认证、授权规则
        // 即采用方式二来控制权限
        http.authorizeRequests().anyRequest().permitAll();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenService);
    }

    /**
     * 客户端服务
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "security.cloud.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }
}

