package com.board.boardsite.service.chat;


import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.dto.chat.ChatRoomPersonDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.chat.ChatRepository;
import com.board.boardsite.repository.chat.ChatRoomPersonRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final TripUserRepository tripUserRepository;
    private final ChatRoomPersonRepository chatRoomPersonRepository;

    public Page<ChatRoomDto> roomList(Pageable pageable) {
        return chatRepository.findByDeletedAndPublicRoom(false,true,pageable).map(ChatRoomDto::from);
    }

    @Transactional
    public void roomSave(ChatRoomDto dto , Long id) {
        var chatRoom =  chatRepository.saveAndFlush(dto.toEntity());
        chatRoom.personCountPlus(chatRoom.getRoomPersonIngCount());
        var tripUser = tripUserRepository.getReferenceById(id);
        var chatRoomPerson   = ChatRoomPerson.of(chatRoom,tripUser);
        chatRoomPersonRepository.save(chatRoomPerson);
    }

}
