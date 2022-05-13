package com.example.cloud.infrastructure.infrastructure.service;

import com.example.cloud.infrastructure.infrastructure.security.AuthenticUser;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author jintaoZou
 * @date 2022/5/11-15:00
 */
@Component
public class CloudUserRepository {

    @Inject
    private CloudUserServiceClient userServiceClient;

    public AuthenticUser getAuthenticUser(String userName){
        return Optional.of(userServiceClient.findAuthUserByName(userName)).orElse(AuthenticUser.passenger());
    }

}
