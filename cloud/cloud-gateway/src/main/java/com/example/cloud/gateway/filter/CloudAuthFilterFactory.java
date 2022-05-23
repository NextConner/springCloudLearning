//package com.example.cloud.gateway.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author jintaoZou
// * @date 2022/5/17-17:48
// */
//@Component
//public class CloudAuthFilterFactory extends AbstractGatewayFilterFactory<CloudAuthFilterFactory.Config> {
//
//    @Value("${header.auth:Authorization}")
//    private String authHeader;
//
//    final String AUTH_PATH = "auth";
//
//    public CloudAuthFilterFactory(){
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//
//            ServerHttpRequest request = exchange.getRequest();
//            String path = request.getURI().getPath();
//            if (path.contains(AUTH_PATH)) {
//                chain.filter(exchange);
//                return Mono.empty();
//            }
//            ServerHttpResponse response = exchange.getResponse();
//
//            HttpHeaders headers = request.getHeaders();
//
//            if (headers.containsKey(authHeader) && StringUtils.isNotBlank(headers.getFirst(authHeader))) {
//                String token = headers.getFirst(authHeader);
//            } else {
//                // 响应消息内容对象
//                JSONObject message = new JSONObject();
//                // 响应状态
//                message.put("code", HttpStatus.UNAUTHORIZED.value());
//                // 响应内容
//                message.put("msg", "缺少token凭证");
//                // 转换响应消息内容对象为字节
//                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//                DataBuffer buffer = response.bufferFactory().wrap(bits);
//                // 设置响应对象状态码 401
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
//                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//                // 返回响应对象
//                return response.writeWith(Mono.just(buffer));
//            }
//            chain.filter(exchange);
//            return Mono.empty();
//        };
//    }
//
//
//    /**
//     * 实现一个内部的Config 类
//     */
//    protected static class Config{
//
//        static ConcurrentHashMap<String,String> innerConfig = new ConcurrentHashMap<>();
//
//        public static void setConfig(String key,String value){
//            innerConfig.put(key,value);
//        }
//
//        public static String getConfig(String key){
//            return innerConfig.get(key);
//        }
//
//    }
//
//    @Override
//    public String name() {
//        return "CloudAuth";
//    }
//}
