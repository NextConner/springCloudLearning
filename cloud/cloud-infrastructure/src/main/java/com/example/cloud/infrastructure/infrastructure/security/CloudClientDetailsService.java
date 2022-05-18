package com.example.cloud.infrastructure.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author jintaoZou
 * @date 2022/5/11-10:45
 */
@RefreshScope
@Named
public class CloudClientDetailsService implements ClientDetailsService {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CloudServiceConfigClient cloudServiceConfigClient;

    private ClientDetailsService clientDetailsService;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }


    @PostConstruct
    public void init() throws Exception {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        cloudServiceConfigClient.getList().stream().forEach(resourceDetail -> {
            builder.withClient(resourceDetail.getClientId())
                    .secret(passwordEncoder.encode(resourceDetail.getClientSecret()))
                    .scopes(resourceDetail.getScope())
                    .redirectUris(resourceDetail.getRedirectUris())
                    .authorizedGrantTypes(resourceDetail.getGrantTypes());
        });
        clientDetailsService = builder.build();
    }

    @Bean
    @ConfigurationProperties(prefix = "security.cloud.oauth2.client")
    private BaseOAuth2ProtectedResourceDetails securityClient() {
        return new BaseOAuth2ProtectedResourceDetails();
    }

}
