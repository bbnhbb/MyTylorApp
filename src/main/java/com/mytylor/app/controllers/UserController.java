package com.mytylor.app.controllers;

import com.mytylor.app.Service.UserService;
import com.mytylor.app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController @RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/aboutus")
    private ResponseEntity<String> aboutUs() {
        return new ResponseEntity<>("We are dev under my tylor12", HttpStatus.OK);
    }

    @PostMapping("/register")
    private ResponseEntity<Object> register(@RequestBody User user) {
        User user1 = userService.registerUser(user);
        return ResponseEntity.ok((new HashMap<>()).put("Message", user1.getUsername()));
    }
}
