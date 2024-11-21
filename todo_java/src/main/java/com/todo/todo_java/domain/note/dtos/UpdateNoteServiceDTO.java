package com.todo.todo_java.domain.note.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateNoteServiceDTO {
    private String authorId;
    private String noteId;
    private String title;
    private String column;
    private int position;
}
