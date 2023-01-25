package com.board.boardsite.controller.chat;

import com.board.boardsite.dto.request.chat.ChatRoomRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags ={"채팅방 목록 조회 Controller"})
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @GetMapping("/rooms")
    @ApiOperation(value = "채팅방 조회", notes = "채팅방 목록을 조회 한다.")
    public Response<Page<ChatRoomResponse>> roomList(@PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable) {
        Page<ChatRoomResponse> rooms = chatService.roomList(pageable).map(ChatRoomResponse::from);
        return Response.success(rooms);
    }

    @PostMapping("/new-room")
    @ApiOperation(value = "채팅방 생성", notes = "채팅방을 생성 한다.")
    public Response<String> newRoom(@Valid @RequestBody ChatRoomRequest chatRoomRequest,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        String roomId = chatService.roomSave(chatRoomRequest.toDto(),tripUserPrincipal.id());
        return Response.success(roomId);
    }

    @GetMapping("/enter/{roomId}")
    @ApiOperation(value = "채팅방 입장", notes = "채팅방에 입장을 한다.")
    public Response<Boolean> enterRoom(@PathVariable Long roomId,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var chk = chatService.roomEnter(roomId,tripUserPrincipal.id());
        return Response.success(chk);
    }

    @GetMapping("/{roomId}")
    @ApiOperation(value = "채팅방 제목 조회", notes = "채팅방의 제목을 조회 한다.")
    public Response<String> roomTitle(@PathVariable Long roomId) {
        String roomName = chatService.roomTitle(roomId);
        return Response.success(roomName);
    }




}
