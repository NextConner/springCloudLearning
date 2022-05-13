package com.example.cloud.user.domain;

import com.example.cloud.infrastructure.domain.BaseEntity;
import com.example.cloud.user.consts.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author jintaoZou
 * @date 2022/5/12-10:43
 */
public class Role extends BaseEntity {

    /**
     * 角色优先级
     */
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private CloudRole role;

}
