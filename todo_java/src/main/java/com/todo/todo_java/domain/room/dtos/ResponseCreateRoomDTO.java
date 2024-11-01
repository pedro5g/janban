package com.todo.todo_java.domain.room.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseCreateRoomDTO {
    private String roomId;
    private String roomName;
}
