package com.board.boardsite.config;

import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {



    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT == accessor.getCommand() || StompCommand.SEND == accessor.getCommand() || StompCommand.MESSAGE == accessor.getCommand()) {
            String jwt = accessor.getFirstNativeHeader("Authorization");
            if (jwt == null ||  !jwt.startsWith("Bearer ")) {
                throw new BoardSiteException(ErrorCode.CHAT_ROOM_NOT_PERMISSION);
            }

                  }
        return message;
    }
}