package com.todo.todo_java.domain.room.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.room.dtos.ResponseCreateRoomDTO;
import com.todo.todo_java.domain.room.entity.Room;
import com.todo.todo_java.domain.room.repository.IRoomRepository;


@Service
public class CreateRoomService {

    @Autowired
    private IRoomRepository roomRepository;


    public ResponseCreateRoomDTO execute(Room room) {
        this.roomRepository.create(room);

        return new ResponseCreateRoomDTO(room.getId().toString(), room.getRoomName());
    }

}
