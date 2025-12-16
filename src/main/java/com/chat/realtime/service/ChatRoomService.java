package com.chat.realtime.service;

import com.chat.realtime.model.ChatRoom;
import com.chat.realtime.model.DTO.ChatRoomRequestDTO;
import com.chat.realtime.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Transactional
    public Long getOrCreateChatRoomId(ChatRoomRequestDTO chatRoomRequestDTO) {
        Long userAId = Math.min(chatRoomRequestDTO.senderId(), chatRoomRequestDTO.receiverId());
        Long userBId = Math.max(chatRoomRequestDTO.senderId(), chatRoomRequestDTO.receiverId());

        return chatRoomRepository.findByUserAIdAndUserBId(userAId, userBId)
                .map(ChatRoom::getId)
                .orElseGet(() -> {
                    ChatRoom newRoom = new ChatRoom();
                    newRoom.setUserAId(userAId);
                    newRoom.setUserBId(userBId);
                    ChatRoom savedRoom = chatRoomRepository.save(newRoom);
                    return savedRoom.getId();
                });
    }

}
