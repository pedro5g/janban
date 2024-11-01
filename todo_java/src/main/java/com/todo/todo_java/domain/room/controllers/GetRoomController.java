package com.todo.todo_java.domain.room.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.room.services.GetRoomService;

@RestController
@RequestMapping("/room")
public class GetRoomController {
    @Autowired
    private GetRoomService getRoomService;

    @GetMapping("info/{roomId}")
    ResponseEntity<Object> get(@PathVariable(value = "roomId") String roomId){
         try{
            
            var result = this.getRoomService.execute(roomId);   

            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
