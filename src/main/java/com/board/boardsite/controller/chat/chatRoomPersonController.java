package com.board.boardsite.controller.chat;


import com.board.boardsite.dto.request.chat.ChatRoomPersonRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.chat.ChatRoomPersonResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.chat.ChatRoomPersonService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class chatRoomPersonController {

    private final ChatRoomPersonService chatRoomPersonService;

    @GetMapping("/person/{roomId}")
    public Response<List<ChatRoomPersonResponse>> roomPersonList(@PathVariable Long roomId){
        var chatRoomPerson = chatRoomPersonService.roomPersonList(roomId).stream().map(ChatRoomPersonResponse::from).collect(Collectors.toList());
        return Response.success(chatRoomPerson);
    }

    @DeleteMapping("/person/exit/{chatRoomId}")
    public Response<Boolean> roomPersonDel(@PathVariable Long chatRoomId ,
                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        chatRoomPersonService.roomPersonDel(chatRoomId,tripUserPrincipal.id());
        return Response.success(true);
    }

}
