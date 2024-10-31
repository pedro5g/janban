package com.todo.todo_java.domain.note.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestUpdateNoteDTO {
    @NotNull
    @NotEmpty
    private String title;
    
    @NotNull
    @NotEmpty
    private String column;
}
