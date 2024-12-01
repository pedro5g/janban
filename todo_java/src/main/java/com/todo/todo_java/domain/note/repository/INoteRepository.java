package com.todo.todo_java.domain.note.repository;

import java.util.List;
import java.util.UUID;

import com.todo.todo_java.core.repository.IBaseRepository;
import com.todo.todo_java.domain.note.entity.Note;

public interface INoteRepository extends IBaseRepository<Note> {
    public List<Note> findByAuthorId(UUID authorId);
    public List<Note> findByRoomId(UUID roomId);
    public void updateAll(List<Note> currentItems);
    public boolean compareContent(Note a, Note b);
}
