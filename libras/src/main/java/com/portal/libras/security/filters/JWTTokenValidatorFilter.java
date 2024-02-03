package com.portal.libras.security.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portal.libras.security.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenValidatorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String jwtToken = request.getHeader(SecurityConstants.JWT_HEADER);

        if(jwtToken != null){

            if(!jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) throw new BadCredentialsException("Bearer ausente no token JWT!");

            try{

                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwtToken)
                        .getPayload();

                String email = String.valueOf(claims.get("email"));
                String authorities = (String) claims.get("authorities");

                Authentication authSession = new UsernamePasswordAuthenticationToken(email, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authSession);

            }catch(Exception e){

                throw new BadCredentialsException("Token JWT inválido!");
            }
        }

        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){

        //Não deve executar o filtro na rota de autenticação.
        return request.getServletPath().equals(SecurityConstants.AUTH_ROUTE);
    }
}
