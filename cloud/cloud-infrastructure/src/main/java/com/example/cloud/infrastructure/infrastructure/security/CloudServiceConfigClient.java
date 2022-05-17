package com.example.cloud.infrastructure.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * 使用了自定义的配置前缀-- cloud.client --加载除gateway外需要授权的客户端列表
 * @author jintaoZou
 * @date 2022/5/11-14:11
 */
@RefreshScope
@ConfigurationProperties(prefix = "cloud.oauth2.client")
@Named
public class CloudServiceConfigClient {

    private List<CloudClient> list;

    public List<CloudClient> getList() {
        return list;
    }

    public void setList(List<CloudClient> list) {
        this.list = list;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    protected static class CloudClient {

        private String clientId;
        private String clientSecret;
        private String accessTokenUri;
        private String[] redirectUris;
        private String[] scope;
        private String[] grantTypes;


        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getAccessTokenUri() {
            return accessTokenUri;
        }

        public void setAccessTokenUri(String accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
        }

        public String[] getRedirectUris() {
            return redirectUris;
        }

        public void setRedirectUris(String[] redirectUris) {
            this.redirectUris = redirectUris;
        }

        public String[] getScope() {
            return scope;
        }

        public void setScope(String[] scope) {
            this.scope = scope;
        }

        public String[] getGrantTypes() {
            return grantTypes;
        }

        public void setGrantTypes(String[] grantTypes) {
            this.grantTypes = grantTypes;
        }

        @Override
        public String toString() {
            return String.format("CloudClient:{ clientId:%s , clientSecret:%s , accessTokenUri:%s , redirectUris:%s ,  scope:%s , grantTypes:%s     }",clientId,clientSecret,accessTokenUri,Arrays.toString(redirectUris), Arrays.toString(scope),Arrays.toString(grantTypes));
        }
    }

    @Override
    public String toString() {
        return "CloudServiceConfigClient{" +
                "list:" + list +
                '}';
    }
}
