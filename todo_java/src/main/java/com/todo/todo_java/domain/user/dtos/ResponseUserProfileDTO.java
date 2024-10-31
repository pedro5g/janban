package com.todo.todo_java.domain.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseUserProfileDTO {
    private String name;
    private String email;
}
