package com.board.boardsite.dto.request.chat;

import com.board.boardsite.dto.chat.ChatRoomDto;

public record ChatRoomRequest(
        Boolean publicRoom,
        int roomCount,
        String roomName
) {
    public static ChatRoomRequest of(Boolean publicRoom,
                                     int roomCount,
                                     String roomName) {
        return new ChatRoomRequest(publicRoom,
                roomCount,
                roomName);
    }

    public ChatRoomDto toDto(){
        return ChatRoomDto.of(roomName,
                roomCount,
                publicRoom);
    }
}
