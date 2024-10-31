package com.todo.todo_java.domain.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.user.dtos.RegisterUserDTO;
import com.todo.todo_java.domain.user.entity.User;
import com.todo.todo_java.domain.user.services.CreateUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class RegisterUserController {
    
    @Autowired
    private CreateUserService createUserService;

   
    @PostMapping("/register")
    ResponseEntity<Object> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        try{
            User user = User
            .builder()
            .name(registerUserDTO.getName())
            .email(registerUserDTO.getEmail())
            .password(registerUserDTO.getPassword())
            .build();

            this.createUserService.execute(user);
            
            return ResponseEntity.ok().build();
            
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
