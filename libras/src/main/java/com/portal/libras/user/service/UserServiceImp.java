package com.portal.libras.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.portal.libras.user.dto.UserCompleteDTO;
import com.portal.libras.user.exception.UserFieldIsNullException;
import com.portal.libras.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    
    private UserRepository userRepository;

    private boolean verifyIfUserFieldsIsNull(UserCompleteDTO user){

        return user.fistName() == null || user.lastName() == null || user.email() == null || user.password() == null;
    }

    @Override
    public HttpStatus addUser(UserCompleteDTO user) {

        //Valida se todos os campos vieram na requisição.
        if(verifyIfUserFieldsIsNull(user)) throw new UserFieldIsNullException();

        return HttpStatus.CREATED;
    }
}
