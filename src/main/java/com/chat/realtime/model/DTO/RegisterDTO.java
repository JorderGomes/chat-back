package com.chat.realtime.model.DTO;

import com.chat.realtime.model.UserRole;

public record RegisterDTO(String name, String password, UserRole role) {

    @Override
    public String name() {
        return name;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public UserRole role() {
        return role;
    }
}
