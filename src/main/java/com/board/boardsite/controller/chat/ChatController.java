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

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Response<String> newRoom(@Valid @RequestBody ChatRoomRequest chatRoomRequest,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        String roomId = chatService.roomSave(chatRoomRequest.toDto(),tripUserPrincipal.id());
        return Response.success(roomId);
    }

    @GetMapping("/enter/{roomId}")
    public Response<Boolean> enterRoom(@PathVariable Long roomId,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var chk = chatService.roomEnter(roomId,tripUserPrincipal.id());
        return Response.success(chk);
    }

    @GetMapping("/{roomId}")
    public Response<String> roomTitle(@PathVariable Long roomId) {
        String roomName = chatService.roomTitle(roomId);
        return Response.success(roomName);
    }




}
