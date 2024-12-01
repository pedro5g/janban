package com.todo.todo_java.domain.note.service;

import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.exception.NotAllowedException;
import com.todo.todo_java.domain.note.dtos.DeleteNoteServiceDTO;
import com.todo.todo_java.domain.note.repository.INoteRepository;


@Service
public class DeleteNoteService{
    @Autowired
    INoteRepository noteRepository;


    public UUID execute(DeleteNoteServiceDTO deleteNoteServiceDTO) throws NameNotFoundException, NotAllowedException {
        var note = this.noteRepository.findById(UUID.fromString(deleteNoteServiceDTO.getNoteId())).orElseThrow(
            () -> new NameNotFoundException()
        );

        // if(!note.getAuthorId().equals(UUID.fromString(deleteNoteServiceDTO.getAuthorId()))){
        //     throw new NotAllowedException();
        // }
        var roomID = note.getRoomId();

        this.noteRepository.delete(note);
        
        
        return roomID;

    }
}
