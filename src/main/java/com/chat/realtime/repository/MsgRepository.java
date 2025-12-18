package com.chat.realtime.repository;

import com.chat.realtime.model.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<Msg, Long> {

    Page<Msg> findByRoomId(Long roomId, Pageable pageable);


}