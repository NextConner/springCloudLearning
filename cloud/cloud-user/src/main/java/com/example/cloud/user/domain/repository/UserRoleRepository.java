package com.example.cloud.user.domain.repository;

import com.example.cloud.infrastructure.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jintaoZou
 * @date 2022/5/12-14:17
 */
public interface UserRoleRepository extends CrudRepository<UserRole,Long> {

    /**
     * 通过userId 查找用户角色
     * @param userId
     * @return
     */
    UserRole findByUserId(Long userId);

}
