package com.board.boardsite.dto.response.chat;



import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.dto.chat.ChatRoomMessageDto;
import com.board.boardsite.dto.security.TripUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

public record ChatRoomMessageResponse(
        Long id,
        LocalDateTime createdAt,
        String content,
        Long chatRoomId,
        boolean idChk,
        String nickName
) {
    public static ChatRoomMessageResponse of(Long id,
                                   LocalDateTime createdAt,
                                   String content,
                                   Long chatRoomId,
                                   boolean idChk,
                                   String nickName) {
        return new ChatRoomMessageResponse(
                id,
                createdAt,
                content,
                chatRoomId,
                idChk,
                nickName);
    }

    public static ChatRoomMessageResponse from(ChatRoomMessageDto dto){
        Long authChkLong = 0L;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            var articleAuthChk = Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .map(Authentication::getPrincipal)
                    .map(TripUserPrincipal.class::cast);
            authChkLong = articleAuthChk.get().id();
        }
        return new ChatRoomMessageResponse(
                dto.id(),
                dto.createdAt(),
                dto.content(),
                dto.chatRoomId(),
                dto.tripUser().id() == authChkLong ? true : false,
                dto.tripUser().nickName()
        );
    }

}
