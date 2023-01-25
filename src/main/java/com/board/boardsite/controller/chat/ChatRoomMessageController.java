package com.board.boardsite.controller.chat;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomMessageResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatRoomMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags ={"채팅방 메시지 조회 Controller"})
@RestController
@RequestMapping("/api/chat/detail")
@RequiredArgsConstructor
public class ChatRoomMessageController {

    private final ChatRoomMessageService chatRoomMessageService;

    @GetMapping("/rooms/{roomId}")
    @ApiOperation(value = "채팅방 메시지 조회", notes = "채팅방 메시지를 조회 한다.")
    public Response<List<ChatRoomMessageResponse>> roomEnterAndMessage(@PathVariable Long roomId,
                                                                      @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
       var chatRoomMessageResponse = chatRoomMessageService.roomMessage(roomId,tripUserPrincipal.id()).stream().map(ChatRoomMessageResponse::from).collect(Collectors.toList());
        return Response.success(chatRoomMessageResponse);
    }

    @GetMapping("/rooms/chk")
    @ApiOperation(value = "채팅방 사용자 체크", notes = "채팅방 사용자 체크를 한다.")
    public Response<Long> roomTripUserId(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        return Response.success(tripUserPrincipal.id());
    }

}
