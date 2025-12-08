package com.chat.realtime.controller;

import com.chat.realtime.model.DTO.MsgInputDTO;
import com.chat.realtime.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chat.realtime.model.Msg;

@Controller
public class App {

    @Autowired
    private MsgService msgService;

    @MessageMapping("/chatmessage")
    @SendTo("/chat")
    public Msg sendMesssage(MsgInputDTO input) {
        return msgService.saveMessage(input.userId(), input.content());
    }

}
