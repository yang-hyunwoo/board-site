package com.board.boardsite.controller.chat;

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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatSocketController {

    private final SimpMessagingTemplate template;
    @MessageMapping("/roomList")
    @SendTo("/topic/roomList")
    public String roomList() {
        return "";
    }
}
