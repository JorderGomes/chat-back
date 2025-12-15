package com.chat.realtime.service;

import com.chat.realtime.model.DTO.AuthDTO;
import com.chat.realtime.model.User;
import com.chat.realtime.repository.UserRepository;
import com.chat.realtime.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    private Encryptor encryptor = new Encryptor();

    public User loginOrRegister(AuthDTO authDTO) throws NoSuchAlgorithmException {
        String name = authDTO.name();
        String rawPassword = authDTO.password();
        String encodedPassword = encryptor.encode(rawPassword);

        Optional<User> optUser = userRepository.findByNameAndPassword(name, encodedPassword);

        if (optUser.isPresent()){
            return optUser.get();
        } else {
            User user = new User(name, encodedPassword);
            return userRepository.save(user);
        }
    }


}
