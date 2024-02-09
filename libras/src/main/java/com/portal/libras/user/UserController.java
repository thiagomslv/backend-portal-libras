package com.portal.libras.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.libras.user.dto.UserCompleteDTO;
import com.portal.libras.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private UserService userService;
    
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody UserCompleteDTO completeUser){

        return ResponseEntity.status(userService.addUser(completeUser)).build();
    }
}
