package com.todo.todo_java.domain.note.service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.core.exception.NotAllowedException;
import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.dtos.UpdateNoteServiceDTO;
import com.todo.todo_java.domain.note.repository.NoteRepository;
import com.todo.todo_java.domain.user.repository.UserRepository;

@Service
public class UpdateNoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;


      public AbstractMap.SimpleEntry<String, ResponseGetNotesDTO> execute(UpdateNoteServiceDTO updateNoteServiceDTO) throws NameNotFoundException, NotAllowedException {
        var note = this.noteRepository.findById(UUID.fromString(updateNoteServiceDTO.getNoteId())).orElseThrow(
            () -> new NameNotFoundException()
        );

        var author = this.userRepository.findById(note.getAuthorId()).orElseThrow(
            () -> new NameNotFoundException()
        );

        // if(!note.getAuthorId().equals(UUID.fromString(updateNoteServiceDTO.getAuthorId()))){
        //     throw new NotAllowedException();
        // }

        note.setTitle(updateNoteServiceDTO.getTitle());
        note.setColumn(updateNoteServiceDTO.getColumn());

        this.noteRepository.update(note);

        var result = new ResponseGetNotesDTO(note.getId().toString(), note.getTitle(), note.getColumn(), author.getName() ,note.getCreatedAt().toString());

        AbstractMap.SimpleEntry<String, ResponseGetNotesDTO> tupla = new AbstractMap.SimpleEntry<>(note.getRoomId().toString(), result);

        return tupla;
    }


}
