package com.todo.todo_java.domain.note.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteNoteServiceDTO {
    private String noteId;
    private String authorId;
}
