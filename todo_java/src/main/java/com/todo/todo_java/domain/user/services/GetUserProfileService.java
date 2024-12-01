package com.todo.todo_java.domain.user.services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.user.dtos.ResponseUserProfileDTO;
import com.todo.todo_java.domain.user.repository.IUserRepository;


import javax.naming.NameNotFoundException;

@Service
public class GetUserProfileService {
    

    @Autowired
    private IUserRepository userRepository;

    public ResponseUserProfileDTO execute(String userId) throws NameNotFoundException {
        var user = this.userRepository.findById(UUID.fromString(userId)).orElseThrow(
            () -> new NameNotFoundException()
        );


        return new ResponseUserProfileDTO(user.getName(), user.getEmail());
    }
}
