package com.board.boardsite.repository.chat;

import com.board.boardsite.domain.chat.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatRoom , Long> {

    Page<ChatRoom> findByDeletedAndPublicRoom(boolean deleted , boolean publicRoom , Pageable pageable);

}
