package com.example.cloud.infrastructure.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;

/**
 * @author jintaoZou
 * @date 2022/5/7-11:19
 */
@Entity
@Data
public class User extends BasicUser{

    private String userName;

    private String realName;

    private String password;

}
