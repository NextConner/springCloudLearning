package com.example.cloud.security.config;

import com.example.cloud.infrastructure.infrastructure.security.UsernamePassAuthenticationProvider;
import com.example.cloud.infrastructure.infrastructure.service.CloudUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;

/**
 * @author jintaoZou
 * @date 2022/5/11-15:55
 */
@EnableWebSecurity
@Configuration
public class CloudSecurityConfig extends WebSecurityConfigurerAdapter {


    @Inject
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private UsernamePassAuthenticationProvider userAuthenticationProvider;

    @Inject
    private CloudUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl().disable();
        http.formLogin().and().authorizeRequests().antMatchers("/**").permitAll();
    }

    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("test").password(passwordEncoder.encode("123456"))
                .authorities("user.C")
                        .roles("USER");
        //配置获取授权用户信息provider
//        auth.authenticationProvider(userAuthenticationProvider);
//        auth.userDetailsService(userDetailService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
