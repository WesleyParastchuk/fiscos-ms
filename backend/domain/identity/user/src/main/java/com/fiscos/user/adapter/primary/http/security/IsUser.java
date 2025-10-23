package com.fiscos.user.adapter.primary.http.security;

import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole('USER')")
public @interface IsUser {
}