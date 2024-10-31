package com.todo.todo_java.domain.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_java.domain.user.services.GetUserProfileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class GetUserProfileController {
    @Autowired
    GetUserProfileService getUserProfileService;


    @GetMapping("/me")
     public ResponseEntity<Object> getProfile(HttpServletRequest request){
        try {
            var authorId = request.getAttribute("userId").toString();

            var result = this.getUserProfileService.execute(authorId);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
