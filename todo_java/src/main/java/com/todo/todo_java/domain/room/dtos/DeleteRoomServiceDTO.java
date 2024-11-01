package com.todo.todo_java.domain.room.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteRoomServiceDTO {
    private String roodId;
    private String authorId;
}
