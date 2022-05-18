package com.example.cloud.gateway.repository;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jintaoZou
 * @date 2022/5/18-15:13
 */
@Component
public class CloudAuthorizedClientRepository implements ServerOAuth2AuthorizedClientRepository {

    @Override
    public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String registrationId, Authentication authentication, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<Void> saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<Void> removeAuthorizedClient(String registrationId, Authentication authentication, ServerWebExchange serverWebExchange) {
        return null;
    }
}
