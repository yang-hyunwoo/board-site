package com.board.boardsite.config;

import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.service.user.TripUserService;
import com.board.boardsite.support.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.Objects;

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



    //    @Override
//    public void postSend(Message message, MessageChannel channel, boolean sent) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        String sessionId = accessor.getSessionId();
//        switch (accessor.getCommand()) {
//            case CONNECT:
//                // ????????? Websocket?????? connect()??? ??? ??? ?????????
//
//                break;
//            case DISCONNECT:
//                log.info("DISCONNECT");
//                log.info("sessionId: {}",sessionId);
//                log.info("channel:{}",channel);
//                // ????????? Websocket?????? disconnect() ??? ??? ??? ????????? or ????????? ???????????? ??? ?????????(????????? ??????~ ???????????? ?????? ???)
//                break;
//            default:
//                break;
//        }
//
//    }
}