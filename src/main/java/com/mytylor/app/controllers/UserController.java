package com.mytylor.app.controllers;

import com.mytylor.app.Service.UserService;
import com.mytylor.app.constants.SecurityConstants;
import com.mytylor.app.entity.User;
import com.mytylor.app.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController @RequiredArgsConstructor
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/aboutus")
    private ResponseEntity<String> aboutUs() {
        return new ResponseEntity<>("We are dev under my tylor12", HttpStatus.OK);
    }

    @PostMapping("/register")
    private ResponseEntity<Object> register(@RequestBody User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
//                User.builder()
//                        .enabled(true)
//                        .username(userCredentialsDto.getUsername())
//                        .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
//                        .roles(Set.of("USER"))
//                        .build();

        
        user1 = userService.registerUser(user1);
        return new ResponseEntity<Object>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<Object> login(@RequestBody User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        Boolean isAuthenticated = userService.loginService(user1);
        if (isAuthenticated) {
            Map<String, Object> map = new HashMap<>();
            String jwt = jwtUtil.generateToken(user.getUsername());
            map.put("message", "user login");
            map.put(SecurityConstants.JWT_HEADER, jwt);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(SecurityConstants.JWT_HEADER, jwt);
            return new ResponseEntity<Object>(map, responseHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
        }

    }
}
