package com.chat.realtime.controller;

import com.chat.realtime.model.Msg;
import com.chat.realtime.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping
    public Page<Msg> getMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return msgService.findRecentMessages(page, size);
    }

}
