package com.chat.realtime.exceptions;

public class ChatRoomNotFoundException extends RuntimeException {
    public ChatRoomNotFoundException(String message) {
        super(message);
    }

    public ChatRoomNotFoundException() {
        super("Chat room não encontrada");
    }
}
