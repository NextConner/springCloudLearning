package com.example.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author jintaoZou
 * @date 2022/5/19-9:55
 */
public class CloudAuthFilter implements GlobalFilter, Ordered {

    @Value("${header.auth.key:Authorization}")
    private String authHeader;

    /**
     *无需授权的地址
     */
    @Value("${header.off.auth.path:oauth,login,logout,register}")
    private String AUTH_PATH_LIST;

    private final String SPLIT_SIGN = ".";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (Arrays.stream(AUTH_PATH_LIST.split(SPLIT_SIGN)).noneMatch(unAuthPath -> path.contains(unAuthPath))) {
            //无需授权处理
            chain.filter(exchange);
            return Mono.empty();
        }

        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders headers = request.getHeaders();

        if (headers.containsKey(authHeader) && StringUtils.isNotBlank(headers.getFirst(authHeader))) {
            String token = headers.getFirst(authHeader);
        } else {
            // 响应消息内容对象
            JSONObject message = new JSONObject();
            // 响应状态
            message.put("code", HttpStatus.UNAUTHORIZED.value());
            // 响应内容
            message.put("msg", "缺少token凭证");
            // 转换响应消息内容对象为字节
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            // 设置响应对象状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            // 返回响应对象
            return response.writeWith(Mono.just(buffer));
        }
        chain.filter(exchange);
        return Mono.empty();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
