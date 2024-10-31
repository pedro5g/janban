package com.todo.todo_java.domain.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.user.dtos.AuthenticateUserDTO;
import com.todo.todo_java.domain.user.services.AuthenticateUserService;

@RestController
@RequestMapping("/user")
public class AuthenticateUserController {
    

    @Autowired
    AuthenticateUserService authenticateUserService;

    @PostMapping("/auth")
    ResponseEntity<Object> auth(@RequestBody AuthenticateUserDTO authenticateUserDTO){
        try{
            var result = this.authenticateUserService.execute(authenticateUserDTO);
            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    } 
}
