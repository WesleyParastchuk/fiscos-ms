package com.fiscos.user.adapter.primary.http.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Componente que substitui o comportamento padrão do Spring Security (página de login).
 * Quando um usuário não autenticado tenta acessar um recurso protegido,
 * ele simplesmente retorna o status 401 (Não Autorizado).
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, 
                         HttpServletResponse response, 
                         AuthenticationException authException) throws IOException {
        
        // Envia o erro 401 para o cliente
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado");
    }
}