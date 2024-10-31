package com.todo.todo_java.domain.note.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.note.dtos.CreateNoteDTO;
import com.todo.todo_java.domain.note.entity.Note;
import com.todo.todo_java.domain.note.service.CreateNoteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/note")
public class CreateNoteController {
    @Autowired
    CreateNoteService createNoteService;

    @PostMapping("/create")
    ResponseEntity<Object> create(@Valid @RequestBody CreateNoteDTO createNoteDTO, HttpServletRequest request){
      
        try {
            var authorId = request.getAttribute("userId").toString();
            
            Note note = Note.builder()
            .authorId(UUID.fromString(authorId))
            .title(createNoteDTO.getTitle())
            .column(createNoteDTO.getColumn())
            .build();

            var result = this.createNoteService.execute(note);

        
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
