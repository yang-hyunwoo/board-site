package com.board.boardsite.dto.chat;

import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.domain.chat.ChatRoomMessage;
import com.board.boardsite.dto.user.TripUserDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ChatRoomMessageDto(
        Long id,
        ChatRoomDto chatRoom,
        TripUserDto tripUser,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ChatRoomMessageDto of(Long id,
                              ChatRoomDto chatRoom,
                              TripUserDto tripUser,
                              String content,
                              LocalDateTime createdAt,
                              String createdBy,
                              LocalDateTime modifiedAt,
                              String modifiedBy) {
        return new ChatRoomMessageDto(
                id,
                chatRoom,
                tripUser,
                content,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static ChatRoomMessageDto of(ChatRoomDto chatRoom,
                                        TripUserDto tripUser,
                                        String content
    ) {
        return new ChatRoomMessageDto(null,
                chatRoom,
                tripUser,
                content,
                null,
                null,
                null,
                null);
    }

    public static ChatRoomMessageDto from(ChatRoomMessage entity) {
        return new ChatRoomMessageDto(
                entity.getId(),
                ChatRoomDto.from(entity.getChatRoom()),
                TripUserDto.from(entity.getTripUser()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
