package com.chat.realtime.repository;


import com.chat.realtime.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByName(String name);

    Optional<User> findByNameAndPassword(String name, String password);

    List<User> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
