package com.todo.todo_java.domain.note.service;

import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.exception.NotAllowedException;
import com.todo.todo_java.domain.note.dtos.UpdateNoteServiceDTO;
import com.todo.todo_java.domain.note.repository.NoteRepository;

@Service
public class UpdateNoteService {
    @Autowired
    NoteRepository noteRepository;


      public void execute(UpdateNoteServiceDTO updateNoteServiceDTO) throws NameNotFoundException, NotAllowedException {
        var note = this.noteRepository.findById(UUID.fromString(updateNoteServiceDTO.getNoteId())).orElseThrow(
            () -> new NameNotFoundException()
        );

        if(!note.getAuthorId().equals(UUID.fromString(updateNoteServiceDTO.getAuthorId()))){
            throw new NotAllowedException();
        }

        note.setTitle(updateNoteServiceDTO.getTitle());
        note.setColumn(updateNoteServiceDTO.getColumn());

        this.noteRepository.update(note);
    }


}
