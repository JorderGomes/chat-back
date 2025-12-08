package com.chat.realtime.model.DTO;


public record MsgInputDTO(Long userId, String content) {

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public String content() {
        return content;
    }
}
