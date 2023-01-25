package com.board.boardsite.controller.chat;


import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomPersonResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatRoomPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags ={"채팅방 인원 관련 Controller"})
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class chatRoomPersonController {

    private final ChatRoomPersonService chatRoomPersonService;

    @GetMapping("/person/{roomId}")
    @ApiOperation(value = "채팅방 인원 조회", notes = "채팅방 인원을 조회 한다.")
    public Response<List<ChatRoomPersonResponse>> roomPersonList(@PathVariable Long roomId){
        var chatRoomPerson = chatRoomPersonService.roomPersonList(roomId).stream().map(ChatRoomPersonResponse::from).collect(Collectors.toList());
        return Response.success(chatRoomPerson);
    }

    @DeleteMapping("/person/exit/{chatRoomId}")
    @ApiOperation(value = "채팅방 나가기", notes = "채팅방을 나간다.")
    public Response<Boolean> roomPersonDel(@PathVariable Long chatRoomId ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        chatRoomPersonService.roomPersonDel(chatRoomId,tripUserPrincipal.id());
        return Response.success(true);
    }

}
