package com.todo.todo_java.domain.room.repository;

import org.springframework.stereotype.Service;

import com.todo.todo_java.core.repository.Repository;
import com.todo.todo_java.domain.room.entity.Room;

@Service
public class RoomRepository extends Repository<Room> implements IRoomRepository {}
