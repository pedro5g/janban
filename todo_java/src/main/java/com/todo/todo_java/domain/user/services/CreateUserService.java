package com.todo.todo_java.domain.user.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.exception.ConflictException;
import com.todo.todo_java.domain.user.entity.User;
import com.todo.todo_java.domain.user.repository.UserRepository;

@Service
public class CreateUserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(User user) throws ConflictException{
        // check if email already exist 
        boolean has =  this.repository
        .findByEmail(user.getEmail()).isPresent();

        
        if(has) {
           throw new ConflictException("Email already registered with another user");
        }
        
        var passwordHash = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);

        this.repository.create(user);

    }

}
