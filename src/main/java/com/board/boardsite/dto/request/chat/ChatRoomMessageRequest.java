package com.board.boardsite.dto.request.chat;

import com.board.boardsite.dto.chat.ChatRoomMessageDto;
import com.board.boardsite.dto.user.TripUserDto;

public record ChatRoomMessageRequest(
        Long chatRoomId,
        String content,
        Long TripUserId
) {
    public static ChatRoomMessageRequest of(Long chatRoomId,
                                  String content,
                                   Long TripUserId) {
        return new ChatRoomMessageRequest(
                chatRoomId,
                content,
                TripUserId);
    }

    public ChatRoomMessageDto toDto(TripUserDto tripUserDto) {
        return ChatRoomMessageDto.of(
                chatRoomId,
                tripUserDto,
                content
        );
    }
}
