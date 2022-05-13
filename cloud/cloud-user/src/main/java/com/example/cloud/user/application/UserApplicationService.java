package com.example.cloud.user.application;

import com.example.cloud.infrastructure.domain.User;
import com.example.cloud.infrastructure.infrastructure.security.AuthenticUser;
import com.example.cloud.user.domain.Permission;
import com.example.cloud.user.domain.RolePermission;
import com.example.cloud.user.domain.UserRole;
import com.example.cloud.user.domain.repository.PermissionRepository;
import com.example.cloud.user.domain.repository.RolePermissionRepository;
import com.example.cloud.user.domain.repository.UserRepository;
import com.example.cloud.user.domain.repository.UserRoleRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户服务应用接口层
 * @author jintaoZou
 * @date 2022/5/7-11:48
 */
@Named
@Transactional(rollbackFor = Exception.class)
public class UserApplicationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserRoleRepository userRoleRepository;

    @Inject
    private RolePermissionRepository rolePermissionRepository;

    @Inject
    private PermissionRepository permissionRepository;


    /**
     * @param user
     */
    public void createUser(User user){
      userRepository.save(user);
    }

    public AuthenticUser findByUserName(String name){
        AuthenticUser authenticUser = new AuthenticUser(userRepository.findByUserName(name));
        UserRole userRole = Optional.of(userRoleRepository.findByUserId(authenticUser.getId()))
                .orElseThrow(() -> new RuntimeException(String.format("用户:%s 对应的角色信息不存在!", name)));

        List<RolePermission> rolePermissions = Optional.of(rolePermissionRepository.findByRoleId(userRole.getRoleId()))
                .orElseThrow(() -> new RuntimeException(String.format("用户:%s 未设置权限信息!", name)));

        List<Permission> permissions = new ArrayList<>();
        rolePermissions.stream().forEach( rolePerm -> permissions.add(permissionRepository.findById(rolePerm.getId()).get()));

        authenticUser.setAuthorities(permissions);
        return authenticUser;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public List<User> findActiveUser(){
        return Arrays.stream(Iterators.toArray(userRepository.findAll().iterator(), User.class)).collect(Collectors.toList());
    }

}
