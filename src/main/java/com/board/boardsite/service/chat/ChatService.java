package com.board.boardsite.service.chat;


import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Page<ChatRoomDto> roomList(Pageable pageable) {
        return chatRepository.findByDeletedAndPublicRoom(false,true,pageable).map(ChatRoomDto::from);
    }

    @Transactional
    public void roomSave(ChatRoomDto dto) {
        chatRepository.save(dto.toEntity());
    }
}
