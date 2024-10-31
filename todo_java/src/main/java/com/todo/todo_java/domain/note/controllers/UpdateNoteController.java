package com.todo.todo_java.domain.note.controllers;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.note.dtos.RequestUpdateNoteDTO;
import com.todo.todo_java.domain.note.dtos.UpdateNoteServiceDTO;

import com.todo.todo_java.domain.note.service.UpdateNoteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/note")
public class UpdateNoteController {
    @Autowired
    UpdateNoteService UpdateNoteService;


    @PatchMapping("/{nodeId}")
    ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable(value = "nodeId") String noteId, @RequestBody RequestUpdateNoteDTO requestUpdateNoteDTO) {
         try {
            var authorId = request.getAttribute("userId").toString();

            this.UpdateNoteService.execute(new UpdateNoteServiceDTO(authorId, noteId, requestUpdateNoteDTO.getTitle(), requestUpdateNoteDTO.getColumn()));

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if(e instanceof NameNotFoundException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            
        }
    }
}
