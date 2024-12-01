package com.todo.todo_java.domain.room.services;

import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.exception.NotAllowedException;
import com.todo.todo_java.domain.room.dtos.DeleteRoomServiceDTO;
import com.todo.todo_java.domain.room.repository.IRoomRepository;


@Service
public class DeleteRoomService {
    
    @Autowired
    private IRoomRepository roomRepository;

    public void execute(DeleteRoomServiceDTO deleteRoomServicedDto) throws NameNotFoundException, NotAllowedException {
        var room = this.roomRepository.findById(UUID.fromString(deleteRoomServicedDto.getRoodId()))
        .orElseThrow(() -> new NameNotFoundException());


        if(!room.getAuthorId().toString().equals(deleteRoomServicedDto.getAuthorId())){
            throw new  NotAllowedException();
        }

        this.roomRepository.delete(room);
    }


}
