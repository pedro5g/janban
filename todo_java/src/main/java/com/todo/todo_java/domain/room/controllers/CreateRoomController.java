package com.todo.todo_java.domain.room.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.room.dtos.RequestCreateRoomDTO;
import com.todo.todo_java.domain.room.entity.Room;
import com.todo.todo_java.domain.room.services.CreateRoomService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/room")
public class CreateRoomController {
    @Autowired
    private CreateRoomService createRoomService;


    @PostMapping("/create")
    ResponseEntity<Object> create(@RequestBody RequestCreateRoomDTO requestCreateRoomDTO,  HttpServletRequest request) {
         var authorId = request.getAttribute("userId").toString();
         Room room = Room.builder()
         .authorId(UUID.fromString(authorId))
         .roomName(requestCreateRoomDTO.getRoomName())
         .build();
        var result = this.createRoomService.execute(room);

        return ResponseEntity.ok().body(result);

    }

}
