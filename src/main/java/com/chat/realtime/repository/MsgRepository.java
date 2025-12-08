package com.chat.realtime.repository;

import com.chat.realtime.model.Msg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<Msg, Long> {



}