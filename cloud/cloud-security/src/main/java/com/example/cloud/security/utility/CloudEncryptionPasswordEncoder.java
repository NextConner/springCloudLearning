package com.example.cloud.security.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import java.util.Optional;

/**
 * @author jintaoZou
 * @date 2022/5/11-10:55
 */
@Named
public class CloudEncryptionPasswordEncoder {

    /**
     * 配置认证使用的密码加密算法：BCrypt
     * 由于在Spring Security很多验证器中都要用到{@link PasswordEncoder}的加密，所以这里要添加@Bean注解发布出去
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 使用默认加密算法进行编码
     */
    public String encode(CharSequence rawPassword) {
        return passwordEncoder().encode(Optional.ofNullable(rawPassword).orElse(""));
    }

}

