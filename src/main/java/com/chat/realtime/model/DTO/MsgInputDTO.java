package com.chat.realtime.model.DTO;


public record MsgInputDTO(Long userId, String content, Long roomId) {

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public Long roomId() {
        return roomId;
    }
}
