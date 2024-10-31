package com.todo.todo_java.domain.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticatedResponseDTO {
    private  String access_token;
}
