package com.chat.realtime.model.DTO;

public record LoginResponseDTO(String token, Long id, String name) {

    @Override
    public String token() {
        return token;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}
