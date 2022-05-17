package com.example.cloud.user.domain.repository;

import com.example.cloud.infrastructure.domain.RolePermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jintaoZou
 * @date 2022/5/12-14:24
 */
public interface RolePermissionRepository extends CrudRepository<RolePermission,Long> {

    /**
     * 查找角色权限
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(Long roleId);

}
