package com.example.cloud.user.domain.repository;

import com.example.cloud.user.domain.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jintaoZou
 * @date 2022/5/12-14:25
 */
public interface PermissionRepository extends CrudRepository<Permission,Long> {
    
}
