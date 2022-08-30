package com.board.boardsite.controller.chat;

import com.board.boardsite.config.TestSecurityConfig;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.dto.request.article.ArticleRequest;
import com.board.boardsite.dto.request.chat.ChatRoomRequest;
import com.board.boardsite.service.chat.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("채팅 - 게시글")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class ChatControllerTest {

    @Autowired
    private final MockMvc mvc;

    @MockBean
    private ChatService chatService;

    @Autowired
    private ObjectMapper objectMapper;



    public ChatControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET][controller] 채팅방 리스트 조회 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingchatRoomList_thenReturnsChatRoomList() throws Exception {
        // Given

        given(chatService.roomList(any(Pageable.class))).willReturn(Page.empty());

        // When & Then
        mvc.perform(get("/api/chat/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        then(chatService).should().roomList(any(Pageable.class));
    }

    @DisplayName("[POST][controller] 채팅방 신규 등록  - 정상 호출")
    @Test
    @WithUserDetails(value = "gusdnTest", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    public void givenChat_whenRequestingChat_thenReturns() throws Exception {
        // Given

        ChatRoomRequest chatRoomRequest = ChatRoomRequest.of(true, 5, "aaaaa");
        willDoNothing().given(chatService).roomSave(any(ChatRoomDto.class),eq(null));
        // When & Then
        mvc.perform(post("/api/chat/new-room")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(chatRoomRequest)))
                .andDo(print())
                .andExpect(status().isOk());
        then(chatService).should().roomSave(any(ChatRoomDto.class),eq(null));

    }

    @DisplayName("[POST][controller] 채팅방 입장   - 정상 호출")
    @Test
    @WithUserDetails(value = "gusdn5162@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void givenChatRoom_whenRequestingChatRoom_thenReturns() throws Exception {
        // Given

        willDoNothing().given(chatService).roomEnter(eq(1L),eq(1L));
        // When & Then
        mvc.perform(get("/api/chat/enter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}