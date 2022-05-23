package com.example.cloud.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jintaoZou
 * @date 2022/5/18-9:20
 */
public class CloudWebSecurity extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl().disable();
        http.authorizeRequests().antMatchers("**/oauth/**").permitAll();
    }
}
