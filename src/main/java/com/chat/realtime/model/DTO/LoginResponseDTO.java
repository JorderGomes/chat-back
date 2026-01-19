package com.chat.realtime.model.DTO;

public record LoginResponseDTO(String token) {

    @Override
    public String token() {
        return token;
    }
}
