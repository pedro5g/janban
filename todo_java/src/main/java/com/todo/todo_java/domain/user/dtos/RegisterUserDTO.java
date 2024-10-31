package com.todo.todo_java.domain.user.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotNull
    private String name;
    
    @Email
    @NotNull
    private String email;
    
    @NotNull
    @Length(min = 6, max = 100)
    private String password;
}
