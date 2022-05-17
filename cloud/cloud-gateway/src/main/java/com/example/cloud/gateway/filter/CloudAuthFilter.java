package com.example.cloud.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jintaoZou
 * @date 2022/5/17-17:48
 */
@Component
public class CloudAuthFilter implements GlobalFilter, Ordered {

    @Value("${header.auth:X-Access-Token}")
    private String authHeader;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        HttpHeaders headers = request.getHeaders();

        if(headers.containsKey(authHeader) && StringUtils.isNotBlank(headers.getFirst(authHeader))){
            //TODO 获取到被增强过的TOKEN信息，并获取用户权限，判断用户是否可以访问当前路径
            String token = headers.getFirst(authHeader);

        }

        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
