package com.todo.todo_java.core.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.todo.todo_java.core.entities.Entity;

public interface IBaseRepository<T extends Entity> {
    public void create(T entity) ;
    public void update(T entity) ;
    public void delete(T entity);
    public void deleteAll(ArrayList<T> entities);
    public ArrayList<T> getMany() ;
    public Optional<T> findById(UUID id); 
}
