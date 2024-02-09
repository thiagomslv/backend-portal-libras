package com.portal.libras.user.service;

import org.springframework.http.HttpStatus;

import com.portal.libras.user.dto.UserCompleteDTO;

public interface UserService {
    
    HttpStatus addUser(UserCompleteDTO user);
}
