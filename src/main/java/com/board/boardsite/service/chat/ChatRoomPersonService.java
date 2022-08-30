package com.board.boardsite.service.chat;

import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.chat.ChatRoomPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomPersonService {

    private final ChatRoomPersonRepository chatRoomPersonRepository;

    @Transactional(readOnly = true)
    public ChatRoomPerson roomChk(Long id , Long UserId) {
        var chatRoomPerson = chatRoomPersonRepository.findByChatRoomIdAndTripUser_Id(id, UserId).orElseThrow(()->new BoardSiteException(ErrorCode.CHAT_ROOM_NOT_PERMISSION));
        return chatRoomPerson;

    }
}
