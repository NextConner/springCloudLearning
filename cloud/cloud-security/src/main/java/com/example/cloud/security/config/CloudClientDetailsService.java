package com.example.cloud.security.config;

import com.example.cloud.security.utility.CloudEncryptionPasswordEncoder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
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
    private CloudEncryptionPasswordEncoder passwordEncoder;

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
        cloudServiceConfigClient.getList().forEach(resourceDetail -> {
            builder.withClient(resourceDetail.getClientId())
                    .secret(passwordEncoder.encode(resourceDetail.getClientSecret()))
                    .scopes(resourceDetail.getScope())
                    .redirectUris(resourceDetail.getRedirectUris())
                    .authorizedGrantTypes(resourceDetail.getGrantTypes());
        });
        clientDetailsService = builder.build();
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    private BaseOAuth2ProtectedResourceDetails securityClient() {
        return new BaseOAuth2ProtectedResourceDetails();
    }
}
