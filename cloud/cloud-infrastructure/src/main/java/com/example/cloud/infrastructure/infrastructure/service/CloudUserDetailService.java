package com.example.cloud.infrastructure.infrastructure.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author jintaoZou
 * @date 2022/5/11-14:50
 */
@Named
public class CloudUserDetailService implements UserDetailsService {

    @Inject
    private CloudUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.getAuthenticUser(userName);
    }
}
