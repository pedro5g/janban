package com.todo.todo_java.domain.note.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNoteDTO {
    @NotNull
    private String title;
    
    @NotNull
    private String column;

    @NotNull
    private String roomId;
}
