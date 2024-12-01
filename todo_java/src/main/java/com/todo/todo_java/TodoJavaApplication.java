package com.todo.todo_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJavaApplication.class, args);
		System.out.println("http://localhost:8080/");
	}

}
