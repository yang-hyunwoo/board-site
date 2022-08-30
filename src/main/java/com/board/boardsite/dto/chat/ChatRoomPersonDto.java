package com.board.boardsite.dto.chat;

import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;

import java.io.Serializable;

public record ChatRoomPersonDto(
        Long id,
        ChatRoomDto chatRoom,
        TripUserDto tripUser
)  {
    public static ChatRoomPersonDto of(Long id,
                             ChatRoomDto chatRoom,
                             TripUserDto tripUser) {
        return new ChatRoomPersonDto(
                id,
                chatRoom,
                tripUser
        );
    }

    public static ChatRoomPersonDto of(
                                       ChatRoomDto chatRoom,
                                       TripUserDto tripUser) {
        return new ChatRoomPersonDto(
                null,
                chatRoom,
                tripUser
        );
    }

    public static ChatRoomPersonDto from(ChatRoomPerson entity){
        return new ChatRoomPersonDto(
                entity.getId(),
                ChatRoomDto.from(entity.getChatRoom()),
                TripUserDto.from(entity.getTripUser())
        );
    }
    public ChatRoomPerson toEntity(ChatRoom chatRoom , TripUser tripUser){
        return ChatRoomPerson.of(chatRoom, tripUser);
    }
}
