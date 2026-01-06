package com.chat.realtime.service;

import com.chat.realtime.exceptions.ChatRoomNotFoundException;
import com.chat.realtime.exceptions.UserNotFoundException;
import com.chat.realtime.model.ChatRoom;
import com.chat.realtime.model.DTO.MsgInputDTO;
import com.chat.realtime.model.Msg;
import com.chat.realtime.model.User;
import com.chat.realtime.repository.ChatRoomRepository;
import com.chat.realtime.repository.MsgRepository;
import com.chat.realtime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MsgService {

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Msg saveMessage(MsgInputDTO msgInputDTO) {
        Long userId = msgInputDTO.userId();
        Long roomId = msgInputDTO.roomId();
        String content = msgInputDTO.content();

        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        ChatRoom currentChatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new ChatRoomNotFoundException("Chat room not found"));

        Msg message = new Msg(content, sender, currentChatRoom);
        return msgRepository.save(message);
    }

    public Page<Msg> findRecentMessages(Long roomId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "time"));
        return msgRepository.findByRoomId(roomId, pageRequest);
    }

}
