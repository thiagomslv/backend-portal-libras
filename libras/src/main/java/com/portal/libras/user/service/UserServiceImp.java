package com.portal.libras.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.portal.libras.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    
    private UserRepository userRepository;

    @Override
    public HttpStatus addUser() {
        
        return HttpStatus.CREATED;
    }
}
