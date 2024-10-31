package com.todo.todo_java.core.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;


@Data
public class Entity {
    private UUID id;
    private LocalDateTime createdAt;

    public Entity () {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }
    
}
