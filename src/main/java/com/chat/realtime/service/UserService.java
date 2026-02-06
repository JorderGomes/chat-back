package com.chat.realtime.service;

import com.chat.realtime.model.User;
import com.chat.realtime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findByName(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean existsByName(String name){
        return userRepository.existsByName(name);
    }

}
