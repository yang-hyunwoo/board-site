package com.board.boardsite.service.chat;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.chat.ChatRoom;
import com.board.boardsite.domain.chat.ChatRoomPerson;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.chat.ChatRoomDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.chat.ChatRepository;
import com.board.boardsite.service.aritcle.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("채팅 페이지")
@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private ChatRepository chatRepository;

    @DisplayName("[GET][service] 채팅방 리스트 조회")
    @Test
    void givenNothing_whenSearchChatRoom_thenReturnChatRoom() {
        Pageable pageable = Pageable.ofSize(10);
        given(chatRepository.findByDeletedAndPublicRoom(false,true,pageable)).willReturn(Page.empty());
        Page<ChatRoomDto> chatRoomDtos = chatService.roomList(pageable);
        assertThat(chatRoomDtos).isEmpty();

        then(chatRepository).should().findByDeletedAndPublicRoom(false, true, pageable);
    }

    @DisplayName("[GET][service] 채팅방 입장 후 채팅 리스트를 조회")
    @Test
    void giveNothing_whenChatList_thenReturnChatList() {

    }


    private ChatRoomDto createRoomDto() {
        return ChatRoomDto.of(
                1L,
                "aaaa",
                0,
                0,
                true,
                false,
                null,
                null,
                null,
                null
        );
    }
    private ChatRoom createRoom() {
        return ChatRoom.of(
                "aaaa",
                0,
                0,
                false,
                true

        );
    }

    private TripUser createTripUser2() {
        return TripUser.of(
                "aaa",
                "aaa",
                "gus@naver.com",
                "test",
                "010",
                0,
                Gender.M,
                false,
                false
        );
    }
    private TripUserDto createTripUser() {
        return TripUserDto.of(
                "gus5162@naver.com",
                "aaa",
                "qqll",
                "010",
                "test",
                Gender.M
        );
    }
}