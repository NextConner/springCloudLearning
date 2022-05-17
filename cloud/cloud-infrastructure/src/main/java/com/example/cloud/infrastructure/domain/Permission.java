package com.example.cloud.infrastructure.domain;

import com.example.cloud.infrastructure.consts.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 *  restful 如何描述资源以及资源的操作
 *      ：Path + Method
 *          ：PUT /accounts/ Account account
 *          ：GET /accounts/{accountId}
 *
 * @author jintaoZou
 * @date 2022/5/12-10:50
 */
@Data
@Entity
public class Permission extends BaseEntity implements GrantedAuthority {

    @Enumerated(value = EnumType.STRING)
    private ResourceOperatePermission permission;

    private String resource;

    /**
     * 将资源和一个操作行为作为权限 : user.C = user 资源的 Create，也就是新建用户
     * @return
     */
    @Override
    public String getAuthority() {
        return resource+"."+permission.name();
    }
}
