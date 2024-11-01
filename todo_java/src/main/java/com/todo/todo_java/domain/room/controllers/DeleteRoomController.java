package com.todo.todo_java.domain.room.controllers;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.room.dtos.DeleteRoomServiceDTO;

import com.todo.todo_java.domain.room.services.DeleteRoomService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/room")
public class DeleteRoomController {

    @Autowired
    private DeleteRoomService deleteRoomService;

    @DeleteMapping("/{roomId}")
    ResponseEntity<Object> delete(@PathVariable(value = "roomId") String roomId,  HttpServletRequest request) {
        try{
             var authorId = request.getAttribute("userId").toString();
             this.deleteRoomService.execute(new DeleteRoomServiceDTO(roomId, authorId));   

            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            if(e instanceof NameNotFoundException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
    
}
