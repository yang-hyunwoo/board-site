package com.board.boardsite.dto.response.chat;



import com.board.boardsite.dto.chat.ChatRoomMessageDto;
import com.board.boardsite.dto.security.TripUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

public record ChatRoomRealTimeMessageResponse(
        Long id,
        LocalDateTime createdAt,
        String content,
        Long chatRoomId,
        Long userId,
        String nickName,
        Long profileId
) {
    public static ChatRoomRealTimeMessageResponse of(Long id,
                                                     LocalDateTime createdAt,
                                                     String content,
                                                     Long chatRoomId,
                                                     Long userId,
                                                     String nickName,
                                                     Long profileId) {
        return new ChatRoomRealTimeMessageResponse(
                id,
                createdAt,
                content,
                chatRoomId,
                userId,
                nickName,
                profileId);
    }

    public static ChatRoomRealTimeMessageResponse from(ChatRoomMessageDto dto){
        return new ChatRoomRealTimeMessageResponse(
                dto.id(),
                dto.createdAt(),
                dto.content(),
                dto.chatRoomId(),
                dto.tripUser().id(),
                dto.tripUser().nickName(),
                dto.tripUser().profileId()
        );
    }

}
