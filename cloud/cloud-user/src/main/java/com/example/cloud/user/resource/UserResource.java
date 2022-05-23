package com.example.cloud.user.resource;

import com.example.cloud.infrastructure.domain.User;
import com.example.cloud.infrastructure.infrastructure.security.AuthenticUser;
import com.example.cloud.infrastructure.jaxrs.CommonResponse;
import com.example.cloud.user.anno.ContactPermission;
import com.example.cloud.user.anno.UserCreatePreAuthorize;
import com.example.cloud.user.anno.UserQueryPreAuthorize;
import com.example.cloud.user.anno.UserUpdatePreAuthorize;
import com.example.cloud.user.application.UserApplicationService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * 提供给外部访问的用户资源 , 类似于非DDD 模式下的controller
 * 遵循REST 接口规范
 *
 * @author jintaoZou
 * @date 2022/5/7-11:47
 */
@RefreshScope
@Path("/users")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserApplicationService userApplicationService;

    @PathParam("userName")
    String userName;

    /**
     * 这里的  @PreAuthorize("#oauth2.hasAnyScope('SERVICE')") 用来限制远程访问的授权客户端是否具备 SERVICE 的 scope
     * @return
     */
    @GET
    @Path("/{userName}")
    @PreAuthorize("#oauth2.hasAnyScope('SERVICE') and hasAnyAuthority('COFFEE')")
    public AuthenticUser getUser() {
        return userApplicationService.findByUserName(userName);
    }

    /**
     *  @PostFilter 后置请求过滤, 过滤可迭代集合中的具备 'USER'权限的用户 或者 用户的status 属性为1 的用户
     * @return
     */
    @UserQueryPreAuthorize
    @GET
    @PostFilter("hasPermission(filterObject,'USER') or filterObject.status =1")
    public List<User> getAllActiveUser(){
        return userApplicationService.findActiveUser();
    }

    /**
     * @PreFilter("#u.name != authentication.name")：演示了 防止新增和当前已授权用户名称相同的用户
     * @ContactPermission 自定义元注解
     * @UserCreatePreAuthorize 自定义元注解
     * @param user
     * @return
     */
    @UserCreatePreAuthorize
    @ContactPermission
    @POST
    public Response addUser(User user) {
        return CommonResponse.op(() -> userApplicationService.createUser(user));
    }

    @UserUpdatePreAuthorize
    @PUT
    public Response editUser(User user) {
        return CommonResponse.op(() -> userApplicationService.updateUser(user));
    }


}
