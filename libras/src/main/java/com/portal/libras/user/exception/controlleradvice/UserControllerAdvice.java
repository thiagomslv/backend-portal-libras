package com.portal.libras.user.exception.controlleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portal.libras.user.exception.UserFieldIsNullException;

@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(UserFieldIsNullException.class)
    public ResponseEntity<?> handleUserFieldIsNull(UserFieldIsNullException ex){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Os campos: fistName, lastName, email e password devem estar presentes no cadastro!");
        
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
