package com.todo.todo_java.domain.note.controllers;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.note.dtos.DeleteNoteServiceDTO;
import com.todo.todo_java.domain.note.service.DeleteNoteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/note")
public class DeleteNoteController {
    
    @Autowired
    DeleteNoteService deleteNoteService;


    @DeleteMapping("/{nodeId}")
    ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable(value = "nodeId") String noteId) {
         try {
            var authorId = request.getAttribute("userId").toString();

            this.deleteNoteService.execute(new DeleteNoteServiceDTO(noteId, authorId));

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if(e instanceof NameNotFoundException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            
        }
    }
}
