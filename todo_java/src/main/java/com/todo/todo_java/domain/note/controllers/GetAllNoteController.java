package com.todo.todo_java.domain.note.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.todo.todo_java.domain.note.service.GetAllNotesService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/note")
public class GetAllNoteController {
    
    @Autowired

    GetAllNotesService getAllNotesService;

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll(HttpServletRequest request){
         try {
            var authorId = request.getAttribute("userId").toString();

            var result = this.getAllNotesService.execute(authorId);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
