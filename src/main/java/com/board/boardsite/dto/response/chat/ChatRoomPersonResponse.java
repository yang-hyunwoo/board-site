package com.board.boardsite.dto.response.chat;

import com.board.boardsite.dto.chat.ChatRoomPersonDto;

public record ChatRoomPersonResponse(
        Long id,
        String nickname,
        Long tripUserId
) {
    public static ChatRoomPersonResponse of(Long id,
                                  String nickname,
                                  Long tripUserId) {
        return new ChatRoomPersonResponse(
                id,
                nickname,
                tripUserId
        );
    }

    public static ChatRoomPersonResponse from(ChatRoomPersonDto dto){
        return new ChatRoomPersonResponse(
                dto.id(),
                dto.tripUser().nickName(),
                dto.tripUser().id()
        );
    }

}
