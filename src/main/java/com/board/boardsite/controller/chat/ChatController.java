package com.board.boardsite.controller.chat;

import com.board.boardsite.dto.request.chat.ChatRoomRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @GetMapping("/rooms")
    public Response<Page<ChatRoomResponse>> roomList(@PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable) {
        Page<ChatRoomResponse> rooms = chatService.roomList(pageable).map(ChatRoomResponse::from);
        return Response.success(rooms);
    }

    @PostMapping("/new-room")
    public Response<Boolean> newRoom(@RequestBody ChatRoomRequest chatRoomRequest,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        chatService.roomSave(chatRoomRequest.toDto(),tripUserPrincipal.id());
        return Response.success(true);
    }





}
