package com.board.boardsite.dto.response.chat;

import com.board.boardsite.dto.chat.ChatRoomDto;

import java.time.LocalDateTime;

public record ChatRoomResponse(
        Long id,
        String roomName,
        int roomCount,
        int roomPersonIngCount,
        boolean deleted,
        boolean publicRoom,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static ChatRoomResponse of(Long id,
                            String roomName,
                            int roomCount,
                            int roomPersonIngCount,
                            boolean deleted,
                            boolean publicRoom,
                            LocalDateTime createdAt,
                            String createdBy,
                            LocalDateTime modifiedAt,
                            String modifiedBy) {
        return new ChatRoomResponse(
                id,
                roomName,
                roomCount,
                roomPersonIngCount,
                deleted,
                publicRoom,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static ChatRoomResponse from(ChatRoomDto dto) {
        return new ChatRoomResponse(
                dto.id(),
                dto.roomName(),
                dto.roomCount(),
                dto.roomPersonIngCount(),
                dto.deleted(),
                dto.publicRoom(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}
