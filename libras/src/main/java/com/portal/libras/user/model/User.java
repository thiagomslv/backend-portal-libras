package com.portal.libras.user.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document("users")
@Getter @Setter
public class User{
    
    private String fistName;
    private String lastName;
    private String email;
    private String password;
    private String authority;
}
