package com.portal.libras.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.libras.user.dto.UserCompleteDTO;
import com.portal.libras.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    
    @PostMapping("/signUp")
    public ResponseEntity<HttpStatus> signUp(@RequestBody UserCompleteDTO completeUser){

        return ResponseEntity.status(userService.addUser()).build();
    }
}
