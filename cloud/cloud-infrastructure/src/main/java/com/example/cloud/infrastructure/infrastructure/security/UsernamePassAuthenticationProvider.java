package com.example.cloud.infrastructure.infrastructure.security;

import com.example.cloud.infrastructure.infrastructure.service.CloudUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author jintaoZou
 * @date 2022/5/10-11:07
 */
@Component
public class UsernamePassAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private CloudUserDetailService cloudUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName().toLowerCase();
        String password = (String) authentication.getCredentials();

        UserDetails user = cloudUserDetailService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 认证通过，返回令牌
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

}
