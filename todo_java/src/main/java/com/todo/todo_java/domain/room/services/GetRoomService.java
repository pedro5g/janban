package com.todo.todo_java.domain.room.services;

import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.room.dtos.ResponseCreateRoomDTO;
import com.todo.todo_java.domain.room.repository.IRoomRepository;

@Service
public class GetRoomService {
    @Autowired
    private IRoomRepository roomRepository;


    public ResponseCreateRoomDTO execute(String roomId) throws NameNotFoundException{
        var room = this.roomRepository.findById(UUID.fromString(roomId))
        .orElseThrow(() -> new NameNotFoundException());


        return new ResponseCreateRoomDTO(roomId, room.getRoomName());
    }
}
