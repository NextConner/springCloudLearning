package com.example.cloud.user.domain;

import com.example.cloud.infrastructure.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author jintaoZou
 * @date 2022/5/12-14:06
 */
@Data
@Entity
public class RolePermission extends BaseEntity {

    private String roleId;
    private String permissionId;

}