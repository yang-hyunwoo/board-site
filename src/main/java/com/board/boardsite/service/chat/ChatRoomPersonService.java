package com.board.boardsite.service.chat;

import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomMessageResponse;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.chat.ChatRoomPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomPersonService {

    private final ChatRoomPersonRepository chatRoomPersonRepository;

    @Transactional(readOnly = true)
    public ChatRoomPerson roomChk(Long id , Long UserId) {
        var chatRoomPerson = chatRoomPersonRepository.findByChatRoomIdAndTripUser_Id(id, UserId).orElseThrow(()->new BoardSiteException(ErrorCode.CHAT_ROOM_NOT_PERMISSION));
        return chatRoomPerson;
    }

    @Transactional(readOnly = true)
    public Integer roomUserChk(Long id , Long UserId) {
        var roomUserCount = chatRoomPersonRepository.findByChatRoomIdAndTripUser_Id(id, UserId).stream().toList();
        return roomUserCount.size();
    }
}
