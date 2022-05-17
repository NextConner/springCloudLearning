package com.example.cloud.infrastructure.domain;

import com.example.cloud.infrastructure.consts.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author jintaoZou
 * @date 2022/5/12-10:43
 */
@Entity
@Data
public class Role extends BaseEntity {

    /**
     * 角色优先级
     */
    private int priority;

    @Enumerated(EnumType.STRING)
    private CloudRole role;

}
