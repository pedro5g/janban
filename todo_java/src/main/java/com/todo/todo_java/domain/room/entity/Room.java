package com.todo.todo_java.domain.room.entity;

import java.util.UUID;

import com.todo.todo_java.core.entities.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Room extends Entity {
    private String roomName;
    private UUID authorId;
}
