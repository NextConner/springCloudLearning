package com.example.cloud.user.domain.repository;

import com.example.cloud.infrastructure.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jintaoZou
 * @date 2022/5/7-11:44
 */
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * 通过登录名
     * @param userName 唯一的用户名查找
     * @return
     */
    User findByUserName(String userName);

}
