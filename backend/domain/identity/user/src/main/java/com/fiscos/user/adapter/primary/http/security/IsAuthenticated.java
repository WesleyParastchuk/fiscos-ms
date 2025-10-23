package com.fiscos.user.adapter.primary.http.security;

import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.*;

/**
 * Meta-anotação para Spring Security.
 * Permite o acesso apenas se o usuário estiver autenticado (qualquer role).
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("isAuthenticated()") // Regra que checa se há um usuário logado
public @interface IsAuthenticated {
}