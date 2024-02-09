package com.portal.libras.security.authprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class LibrasAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        
        //Define o tipo de autenticação suportada.
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
