package com.hrm.mockprojecthrm.security;


import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal // người dùng hiện tại đang được xác thực
public @interface CurrentUser {
}
