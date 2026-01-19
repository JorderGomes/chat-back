package com.chat.realtime.controller;

import com.chat.realtime.model.DTO.AuthDTO;
import com.chat.realtime.model.DTO.LoginResponseDTO;
import com.chat.realtime.model.DTO.RegisterDTO;
import com.chat.realtime.model.User;
import com.chat.realtime.repository.UserRepository;
import com.chat.realtime.service.AuthService;
import com.chat.realtime.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.name(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.existsByName(data.name())){
            return ResponseEntity.badRequest().body("Username already exists");
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.name(), encryptedPassword, data.role());
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }

}
