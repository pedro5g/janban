package com.todo.todo_java.domain.user.dtos;

import lombok.Data;

@Data
public class AuthenticateUserDTO {
    private String email;
    private String password;
}
