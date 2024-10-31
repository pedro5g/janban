package com.todo.todo_java.domain.note.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.todo.todo_java.core.entities.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note extends Entity {
   
    @Getter
    private String title;
    
   
    @Getter
    private String column;

    @Getter
    @Setter
    private UUID authorId;

    @Getter
    private LocalDateTime updatedAt;


    private void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }


    public void setColumn(String column) {
        this.column = column;
        this.setUpdatedAt(LocalDateTime.now());
    }
    
    public void setTitle(String title) {
        this.title = title;
        this.setUpdatedAt(LocalDateTime.now());
    }


   
}
