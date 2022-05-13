package com.example.cloud.user.domain;

import com.example.cloud.infrastructure.domain.BaseEntity;
import com.example.cloud.user.consts.*;
import com.example.cloud.user.utility.PermissionAttributeConverter;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

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

    /**
     * {@link PermissionAttributeConverter} 类进行操作行为权限集合和数据库存储字符类型权限的转换
     */
    @Convert(converter = PermissionAttributeConverter.class)
    private Set<ResourceOperatePermission> permission;

    private String resource;

    @Override
    public String getAuthority() {
        return null;
    }
}
