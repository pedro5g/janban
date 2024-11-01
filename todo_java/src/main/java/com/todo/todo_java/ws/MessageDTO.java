package com.todo.todo_java.ws;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageDTO {
    private Object data;
    private String type;
}
