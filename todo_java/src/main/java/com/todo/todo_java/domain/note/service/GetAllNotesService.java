package com.todo.todo_java.domain.note.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_java.domain.note.dtos.ResponseGetNotesDTO;
import com.todo.todo_java.domain.note.repository.NoteRepository;
import com.todo.todo_java.domain.user.repository.UserRepository;

@Service
public class GetAllNotesService {
    @Autowired
    NoteRepository repository;

    @Autowired
    UserRepository userRepository;
    public List<ResponseGetNotesDTO> execute(String authorId) throws NameNotFoundException{

        var id = UUID.fromString(authorId);

        var author = this.userRepository.findById(id).orElseThrow(
            () -> new NameNotFoundException("Author not found")
        );

        List<ResponseGetNotesDTO> dto = new ArrayList<>();
        this.repository.findByAuthorId(id).forEach((e) -> { 
          var format =  new ResponseGetNotesDTO(e.getId().toString(), e.getTitle(), e.getColumn(), e.getPosition(),author.getName() ,e.getCreatedAt().toString());
            dto.add(format);
        });

        return  dto;
    }
}
