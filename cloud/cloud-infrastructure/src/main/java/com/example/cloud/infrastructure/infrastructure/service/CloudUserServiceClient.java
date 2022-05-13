package com.example.cloud.infrastructure.infrastructure.service;

import com.example.cloud.infrastructure.domain.User;
import com.example.cloud.infrastructure.infrastructure.security.AuthenticUser;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author jintaoZou
 * @date 2022/5/11-14:53
 */
@FeignClient(name = "user")
public interface CloudUserServiceClient {

    /**
     * 认证时获取用户以及授权信息
     * @param userName
     * @return
     */
    @GET
    @Path("/restful/users/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    AuthenticUser findAuthUserByName(@PathParam("userName") String userName);

}
