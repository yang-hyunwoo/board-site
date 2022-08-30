package com.board.boardsite.service.chat;

import com.board.boardsite.domain.chat.ChatRoomMessage;
import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.dto.chat.ChatRoomMessageDto;
import com.board.boardsite.dto.response.chat.ChatRoomMessageResponse;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.chat.ChatRepository;
import com.board.boardsite.repository.chat.ChatRoomMessageRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomMessageService {

    private final ChatRoomMessageRepository chatRoomMessageRepository;

    private final ChatRepository chatRepository;
    private final ChatRoomPersonService chatRoomPersonService;

    private final TripUserRepository tripUserRepository;
    @Transactional(readOnly = true)
    public List<ChatRoomMessageDto> roomMessage(Long id , Long UserId) {
       var chatRoomPerson = chatRoomPersonService.roomChk(id, UserId);
       chatRoomPerson.getCreatedAt();
        return  chatRoomMessageRepository.findByChatRoom_IdAndCreatedAtAfterOrderByCreatedAtAsc(chatRoomPerson.getChatRoom().getId(),chatRoomPerson.getCreatedAt()).stream().map(ChatRoomMessageDto::from).collect(Collectors.toList());
    }

    @Transactional
    public ChatRoomMessageDto roomMessageSave(Long id , Long userId ,String content){
        var tripUser = tripUserRepository.getReferenceById(userId);
        var chatRoom = chatRepository.findById(id).orElseThrow(() -> new BoardSiteException(ErrorCode.CHAT_ROOM_NOT_FOUND));

        var chatRoomMessage =ChatRoomMessage.of(chatRoom,tripUser,content);
        var chatRoomMessageId = chatRoomMessageRepository.save(chatRoomMessage).getId();
        return chatRoomMessageRepository.findById(chatRoomMessageId).map(ChatRoomMessageDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.CHAT_ROOM_NOT_FOUND));
    }
}
