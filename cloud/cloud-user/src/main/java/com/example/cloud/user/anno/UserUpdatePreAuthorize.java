package com.example.cloud.user.anno;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jintaoZou
 * @date 2022/5/12-15:27
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("#oauth2.hasAuthority('user.U')")
public @interface UserUpdatePreAuthorize {
}
