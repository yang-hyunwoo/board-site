package com.board.boardsite.controller.chat;

import com.board.boardsite.dto.request.chat.ChatRoomRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomResponse;
import com.board.boardsite.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final SimpAnnotationMethodMessageHandler simpAnnotationMethodMessageHandler;

    @GetMapping("/rooms")
//    @MessageMapping("/socket/roomList")
//    @SendTo("/roomList")
    public Response<Page<ChatRoomResponse>> roomList(@PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable) {
        Page<ChatRoomResponse> rooms = chatService.roomList(pageable).map(ChatRoomResponse::from);
        return Response.success(rooms);
    }

    @PostMapping("/new-room")
    public Response<Boolean> newRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        chatService.roomSave(chatRoomRequest.toDto());
        return Response.success(true);
    }




}
