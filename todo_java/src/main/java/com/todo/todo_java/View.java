package com.todo.todo_java;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class View {
    @RequestMapping(value = "/{path:[^\\.]*}") 
    public String redirect() {
        return "forward:/index.html"; 
    }
}