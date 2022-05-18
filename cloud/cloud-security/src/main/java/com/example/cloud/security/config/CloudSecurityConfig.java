package com.example.cloud.security.config;

import com.example.cloud.infrastructure.infrastructure.security.UsernamePassAuthenticationProvider;
import com.example.cloud.infrastructure.infrastructure.service.CloudUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;

/**
 * @author jintaoZou
 * @date 2022/5/11-15:55
 */
@EnableWebSecurity
@Configuration
public class CloudSecurityConfig extends CloudWebSecurity {


    @Inject
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private UsernamePassAuthenticationProvider userAuthenticationProvider;

    @Inject
    private CloudUserDetailService userDetailService;


    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN","USER","TEST");
        auth.userDetailsService(userDetailService);
        auth.authenticationProvider(userAuthenticationProvider);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
