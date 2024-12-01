package com.todo.todo_java.domain.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.todo.todo_java.core.repository.IBaseRepository;
import com.todo.todo_java.domain.user.entity.User;


public interface IUserRepository extends IBaseRepository<User> {
    public Optional<User> findByEmail(String email);
}
