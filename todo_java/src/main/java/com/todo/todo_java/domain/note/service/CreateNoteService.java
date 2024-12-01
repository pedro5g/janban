package com.todo.todo_java.domain.note.service;



import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.entity.Note;
import com.todo.todo_java.domain.note.repository.INoteRepository;
import com.todo.todo_java.domain.room.repository.IRoomRepository;
import com.todo.todo_java.domain.user.repository.IUserRepository;

@Service
public class CreateNoteService {

    @Autowired 
    INoteRepository repository;

    @Autowired
    IUserRepository userRepository;


    @Autowired
    IRoomRepository roomRepository;

    public ResponseGetNotesDTO execute(Note note) throws NameNotFoundException{
        this.roomRepository.findById(note.getRoomId()).orElseThrow(
            () -> new NameNotFoundException("Room not found")
        );
        var author = this.userRepository.findById(note.getAuthorId()).orElseThrow(
            () -> new NameNotFoundException("Room not found")
        );

        this.repository.create(note);

        return new ResponseGetNotesDTO(note.getId().toString(), note.getTitle(), note.getColumn(), note.getPosition(), author.getName(),note.getCreatedAt().toString());
    }
    
}
