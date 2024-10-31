package com.todo.todo_java.domain.user.entity;

import com.todo.todo_java.core.entities.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends Entity {
    private String name;
    private String email;
    private String password;
}
