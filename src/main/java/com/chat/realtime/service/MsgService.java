package com.chat.realtime.service;

import com.chat.realtime.model.Msg;
import com.chat.realtime.model.User;
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

    public Msg saveMessage(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Msg message = new Msg(content, user);
        return msgRepository.save(message);
    }

    public Page<Msg> findRecentMessages(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "time"));
        return msgRepository.findAll(pageRequest);
    }

}
