package com.example.cloud.user.anno;

import org.springframework.security.access.prepost.PreFilter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 1.继承了 preFilter 注解的元注解 ，可以用在所有具备"USER"类型参数名为 contact 方法上，
 * 2.限定调用此方法的用户参数必须是当前已认证的用户
 * 3.可以通过 org.springframework.security.core.parameters.P 注解指定参数别名为 contact : @P("contact")
 *
 * @author jintaoZou
 * @date 2022/5/10-11:56
 */
@Retention(RetentionPolicy.RUNTIME)
@PreFilter("#contact.name == authentication.name")
public @interface ContactPermission {
}
