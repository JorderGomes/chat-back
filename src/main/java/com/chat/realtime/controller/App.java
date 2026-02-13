package com.chat.realtime.controller;

import com.chat.realtime.model.DTO.MsgInputDTO;
import com.chat.realtime.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chat.realtime.model.Msg;
import org.springframework.messaging.simp.SimpMessagingTemplate;
@Controller
public class App {

    @Autowired
    private MsgService msgService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}/send")
    public Msg sendMessage(@DestinationVariable Long roomId, MsgInputDTO input) {
        Msg savedMessage = msgService.saveMessage(input);
        String destination = "/topic/room/" + roomId;
        messagingTemplate.convertAndSend(destination, savedMessage);
        return savedMessage;
    }

}
