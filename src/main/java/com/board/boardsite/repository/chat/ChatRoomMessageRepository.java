package com.board.boardsite.repository.chat;

import com.board.boardsite.domain.chat.ChatRoomMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatRoomMessageRepository extends JpaRepository<ChatRoomMessage , Long> {


    List<ChatRoomMessage> findByChatRoom_IdAndCreatedAtAfterOrderByCreatedAtAsc(Long id , LocalDateTime createdAt);
}
