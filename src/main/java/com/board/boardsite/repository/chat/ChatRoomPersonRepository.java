package com.board.boardsite.repository.chat;

import com.board.boardsite.domain.chat.ChatRoomPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomPersonRepository extends JpaRepository<ChatRoomPerson , Long> {

    Optional<ChatRoomPerson> findByChatRoomIdAndTripUser_Id(Long id, Long userId);
}
