package com.todo.todo_java.domain.note.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.repository.NoteRepository;

@Service
public class GetAllNotesService {
    @Autowired
    NoteRepository repository;


    public List<ResponseGetNotesDTO> execute(String authorId) throws NameNotFoundException{

        var id = UUID.fromString(authorId);

        List<ResponseGetNotesDTO> dto = new ArrayList<>();
        this.repository.findByAuthorId(id).forEach((e) -> { 
          var format =  new ResponseGetNotesDTO(e.getId().toString(), e.getTitle(), e.getColumn(), e.getCreatedAt().toString());
            dto.add(format);
        });

        return  dto;
    }
}
