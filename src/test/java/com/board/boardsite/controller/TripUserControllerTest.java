package com.board.boardsite.controller;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.dto.request.user.TripUserJoinRequest;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.service.user.TripUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("사용자 로그인 및 회원 가입")
@SpringBootTest
@AutoConfigureMockMvc
class TripUserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TripUserService tripUserService;




    @DisplayName("회원가입 정상 ")
    @Test
    void givenNewTripUser_whenRequesting_thenSavesNewTripUser() throws Exception {

        TripUserDto dto = createTripUserDto();
        given(tripUserService.join(dto)).willReturn(dto);
        mockMvc.perform(post("/api/trip/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new TripUserJoinRequest(dto.name(),
                                dto.nickName(),
                                dto.email(),
                                dto.password(),
                                dto.gender()))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @DisplayName("회원가입시 이미 가입된 이메일로 회원가입 하는 경우 ")
    @Test
    void givenNewTripUser_whenRequesting_thenSavesNewTripUserError() throws Exception {

        TripUserDto dto = createTripUserDto();
        when(tripUserService.join(dto)).thenThrow(new BoardSiteException(ErrorCode.DUPLICATED_EMAIL,""));
        mockMvc.perform(post("/api/trip/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new TripUserJoinRequest(dto.name(),
                                dto.nickName(),
                                dto.email(),
                                dto.password(),
                                dto.gender()))))
                .andDo(print())
                .andExpect(status().isConflict());

    }


    private TripUserDto createTripUserDto() {
        return  TripUserDto.of(
                "aa@email.com",
                "aa",
                "bb",
                "cc",
                Gender.M
        );
    }

}