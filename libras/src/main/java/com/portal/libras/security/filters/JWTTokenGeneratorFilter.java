package com.portal.libras.security.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portal.libras.security.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /*  Se o usuário não estiver autenticado, gera o token JWT. Esse filtro só será executado na rota de login,
            por isso, não há risco de se gerar um token para um usuário não autenticado, uma vez que o AuthenticationProvider
            gera uma exceção em caso de erro.
        */
        if(authentication != null){

            //Gera a chave de assinatura do token JWT.
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

            String jwtToken = Jwts.builder().claim("email", authentication.getName())
                                            .claim("authorities", parseAuthorities(authentication.getAuthorities()))
                                            .issuedAt(new Date())
                                            .expiration(new Date(new Date().getTime() + 30000000))
                                            .signWith(key).compact();

            response.setHeader(SecurityConstants.JWT_HEADER, jwtToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){

        //Só executa o filtro na rota de autenticação.
        return !request.getServletPath().equals(SecurityConstants.AUTH_ROUTE);
    }

    private String parseAuthorities(Collection<? extends GrantedAuthority> collection){

        Set<String> authoritiesSet = new HashSet<>();

        for (GrantedAuthority authority : collection) {

            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
    
}
