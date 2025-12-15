package com.chat.realtime.controller;

import com.chat.realtime.model.DTO.AuthDTO;
import com.chat.realtime.model.User;
import com.chat.realtime.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthDTO authDTO) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(authService.loginOrRegister(authDTO));
    }

}
