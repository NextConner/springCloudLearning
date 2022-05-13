package com.example.cloud.infrastructure.infrastructure.security;

import com.example.cloud.infrastructure.domain.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 使用登录用户初始化的授权用户信息
 * @author jintaoZou
 * @date 2022/5/11-14:58
 */
@NoArgsConstructor
@Setter
public class AuthenticUser extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticUser(User user){
        BeanUtils.copyProperties(user,this);
    }

    public static AuthenticUser passenger() {

        AuthenticUser authenticUser = new AuthenticUser();
        authenticUser.setId(System.currentTimeMillis());
        authenticUser.setPassword("123456");
        authenticUser.setUserName("路人甲");
        return authenticUser;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
