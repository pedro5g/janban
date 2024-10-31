package com.todo.todo_java.domain.note.service;



import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.entity.Note;
import com.todo.todo_java.domain.note.repository.NoteRepository;
import com.todo.todo_java.domain.user.repository.UserRepository;

@Service
public class CreateNoteService {

    @Autowired 
    NoteRepository repository;

    @Autowired
    UserRepository userRepository;

    public ResponseGetNotesDTO execute(Note note) throws NameNotFoundException{
        this.userRepository.findById(note.getAuthorId()).orElseThrow(
            () -> new NameNotFoundException()
        );
        this.repository.create(note);

        return new ResponseGetNotesDTO(note.getId().toString(), note.getTitle(), note.getColumn(),note.getCreatedAt().toString());
    }
    
}