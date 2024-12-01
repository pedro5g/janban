package com.todo.todo_java.domain.user.repository;

import com.todo.todo_java.core.repository.Repository;
import com.todo.todo_java.domain.user.entity.User;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserRepository extends Repository<User> implements IUserRepository{
    public Optional<User> findByEmail(String email) {
        return this.itens
        .stream()
        .filter(item -> item.getEmail().equals(email))
        .findFirst();
    }
}
