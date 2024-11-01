package com.todo.todo_java.domain.note.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.todo.todo_java.core.repository.Repository;
import com.todo.todo_java.domain.note.entity.Note;

@Service
public class NoteRepository extends Repository<Note>{
    public List<Note> findByAuthorId(UUID authorId){
        return this.itens
        .stream()
        .filter((item) -> item.getAuthorId().equals(authorId))
        .toList();
    }

    public List<Note> findByRoomId(UUID roomId) {
        return this.itens
        .stream()
        .filter((item) -> item.getRoomId().equals(roomId))
        .toList();
    }

    public void updateAll(List<Note> currentItems) {
        
        for(Note i : this.itens){   
            for(Note j : currentItems){
                if(!this.compareContent(i, j)){
                    this.itens.set(this.findIndex(i), j);
                }
            }
        }
    }

    private boolean compareContent(Note a, Note b) {
        return a.getColumn().equals(b.getColumn());
    }

   
}
