package com.chat.realtime.controller;

import com.chat.realtime.model.Msg;
import com.chat.realtime.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/{roomId}")
    public Page<Msg> getMessages(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return msgService.findRecentMessages(roomId, page, size);
    }

}
