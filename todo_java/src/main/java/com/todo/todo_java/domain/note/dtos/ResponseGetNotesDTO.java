package com.todo.todo_java.domain.note.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseGetNotesDTO {
    private String id;
    private String title;
    private String column;
    private int position;
    private String authorName;
    private String createdAt;
}
