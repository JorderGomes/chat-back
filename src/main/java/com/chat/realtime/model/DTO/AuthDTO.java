package com.chat.realtime.model.DTO;


public record AuthDTO(String name, String password) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String password() {
        return password;
    }
}
