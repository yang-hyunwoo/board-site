package com.board.boardsite.controller.chat;

import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.dto.request.chat.ChatRoomMessageRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomMessageResponse;
import com.board.boardsite.dto.response.chat.ChatRoomRealTimeMessageResponse;
import com.board.boardsite.dto.response.chat.ChatRoomResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatRoomMessageService;
import com.board.boardsite.service.chat.ChatService;
import com.board.boardsite.service.user.TripUserService;
import com.board.boardsite.support.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatSocketController {

    private final ChatRoomMessageService chatRoomMessageService;

    private SimpAnnotationMethodMessageHandler simpAnnotationMethodMessageHandler;

    //    @Value("${jwt.secret-key}")
    private final String key =  "board_site.trip_web_application.secret_key";

    private  final TripUserService tripUserService;

    @MessageMapping("/roomList")
    @SendTo("/topic/roomList")
    public String roomList() {
        return "";
    }

    @MessageMapping("/new-Message/{roomId}")
    @SendTo("/topic/new-Message/{roomId}")
    public Response<ChatRoomRealTimeMessageResponse> newMessage(ChatRoomMessageRequest chatRoomMessageRequest , @Header("Authorization") String token) {
        final String jwt = token.split(" ")[1].trim();
        String email = JwtTokenUtils.getEmail(jwt , key);
        TripUserPrincipal user = tripUserService.loadUserByEmail(email);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var chatRoomMessageResponse = ChatRoomRealTimeMessageResponse.from(chatRoomMessageService.roomMessageSave(chatRoomMessageRequest.chatRoomId(),chatRoomMessageRequest.TripUserId(),chatRoomMessageRequest.content()));
        return Response.success(chatRoomMessageResponse);
    }

    @MessageMapping("/room-person/{roomId}")
    @SendTo("/topic/room-person/{roomId}")
    public String roomPerson() {
        return "";
    }
}
