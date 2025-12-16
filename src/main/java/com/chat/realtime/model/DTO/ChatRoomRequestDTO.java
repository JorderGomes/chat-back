package com.chat.realtime.model.DTO;

public record ChatRoomRequestDTO(Long senderId, Long receiverId) {

    @Override
    public Long senderId() {
        return senderId;
    }

    @Override
    public Long receiverId() {
        return receiverId;
    }
}
