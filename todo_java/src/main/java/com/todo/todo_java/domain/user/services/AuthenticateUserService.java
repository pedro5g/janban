package com.todo.todo_java.domain.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.providers.JWTProvider;
import com.todo.todo_java.domain.user.dtos.AuthenticateUserDTO;
import com.todo.todo_java.domain.user.dtos.UserAuthenticatedResponseDTO;
import com.todo.todo_java.domain.user.repository.UserRepository;

import javax.naming.AuthenticationException;

@Service
public class AuthenticateUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired 
    JWTProvider jwtProvider;


   public UserAuthenticatedResponseDTO execute(AuthenticateUserDTO authenticateUserDTO) throws AuthenticationException {
        var user = this.repository
        .findByEmail(authenticateUserDTO
        .getEmail())
        .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        boolean isMatches = this.passwordEncoder.matches(authenticateUserDTO.getPassword(), user.getPassword());

        if(!isMatches) {
            throw new AuthenticationException("Invalid credentials");
        }
        String accessToken = this.jwtProvider.createJWT(user.getId().toString());
        return new UserAuthenticatedResponseDTO(accessToken);
   } 
    


}
