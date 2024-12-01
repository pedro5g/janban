package com.todo.todo_java.core.repository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Optional;


import com.todo.todo_java.core.entities.Entity;

public abstract class Repository<T extends Entity> implements IBaseRepository<T> {
    protected ArrayList<T> itens = new ArrayList<>();

    public void create(T entity) {
        this.itens.add(entity);
    }
    public void update(T entity) {
        this.itens.set(this.findIndex(entity), entity);
    }
    public void delete(T entity) {
        this.itens.remove(entity);
    }
    public void deleteAll(ArrayList<T> entities){
        this.itens.removeAll(entities);
    }

    public ArrayList<T> getMany() {
        return this.itens;
    }

    public Optional<T> findById(UUID id) {
        return this.itens
        .stream()
        .filter(item -> item.getId().equals(id))
        .findFirst();
    }

    protected int findIndex (T o) {
        int i = -1;
        for (T c : this.itens) {
            ++i;
            if(c.getId().equals(o.getId())){
                return i;
            }
        }
        return -1;
    }
}
