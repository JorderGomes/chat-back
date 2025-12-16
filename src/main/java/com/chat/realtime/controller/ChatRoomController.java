package com.chat.realtime.controller;

import com.chat.realtime.model.DTO.ChatRoomRequestDTO;
import com.chat.realtime.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/find-or-create")
    public ResponseEntity<Long> getRoomId(@RequestBody ChatRoomRequestDTO chatRoomRequestDTO){
        Long roomId = chatRoomService.findOrCreateRoom(chatRoomRequestDTO);
        return ResponseEntity.ok(roomId);
    }

}
