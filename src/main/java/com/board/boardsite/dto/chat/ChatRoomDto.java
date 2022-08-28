package com.board.boardsite.dto.chat;

import com.board.boardsite.domain.chat.ChatRoom;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ChatRoomDto(
        Long id,
        String roomName,
        int roomCount,
        boolean deleted,
        boolean publicRoom,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy

)  {
    public static ChatRoomDto of(Long id,
                       String roomName,
                       int roomCount,
                       boolean deleted,
                       boolean publicRoom,
                       LocalDateTime createdAt,
                       String createdBy,
                       LocalDateTime modifiedAt,
                       String modifiedBy) {
        return new ChatRoomDto(id,
                roomName,
                roomCount,
                deleted,
                publicRoom,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static ChatRoomDto of(String roomName,
                                 int roomCount,
                                 boolean publicRoom) {
        return new ChatRoomDto(null,
                roomName,
                roomCount,
                false,
                publicRoom,
                null,
                null,
                null,
                null);
    }

    public static ChatRoomDto from(ChatRoom entity){
        return new ChatRoomDto(
                entity.getId(),
                entity.getRoomName(),
                entity.getRoomCount(),
                entity.isDeleted(),
                entity.isPublicRoom(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ChatRoom toEntity() {
        return ChatRoom.of(roomName,
                roomCount,
                deleted,
                publicRoom);
    }
}
