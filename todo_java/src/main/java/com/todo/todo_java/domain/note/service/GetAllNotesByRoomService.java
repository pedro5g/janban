package com.todo.todo_java.domain.note.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.repository.INoteRepository;
import com.todo.todo_java.domain.room.repository.IRoomRepository;
import com.todo.todo_java.domain.user.repository.IUserRepository;

@Service
public class GetAllNotesByRoomService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoomRepository roomRepository;

    @Autowired
    INoteRepository noteRepository;

    public List<ResponseGetNotesDTO> execute(String roodId) throws NameNotFoundException{

        this.roomRepository.findById(UUID.fromString(roodId)).orElseThrow(
            () -> new NameNotFoundException("Author not found")
        );

        List<ResponseGetNotesDTO> dto = new ArrayList<>();
        
        this.noteRepository.findByRoomId(UUID.fromString(roodId)).forEach((e) -> { 
          String authorName = this.getAuthorName(e.getAuthorId());
          var format =  new ResponseGetNotesDTO(e.getId().toString(), e.getTitle(), e.getColumn(), e.getPosition(),authorName,e.getCreatedAt().toString());
            dto.add(format);
        });

        return  dto;
    }


    private String getAuthorName(UUID authorId){
        var author = this.userRepository.findById(authorId);
        if(author.isPresent()){
              return author.get().getName();
        }

        return "";
      
    }
}
