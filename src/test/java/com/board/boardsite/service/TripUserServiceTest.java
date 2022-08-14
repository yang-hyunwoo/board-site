package com.board.boardsite.service;

import com.board.boardsite.domain.TripUser;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.TripUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TripUserServiceTest {

    @Autowired
    private TripUserService tripUserService;

    @MockBean
    private TripUserRepository tripUserRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @DisplayName("회원가입 -Service 정상")
    @Test
    void givenNewTripUser_whenRequesting_thenSavesNewTripUser() {

        TripUserDto dto = createTripUserDto();
        when(tripUserRepository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(encoder.encode(dto.password())).thenReturn("encrypt_password");
        when(tripUserRepository.save(any())).thenReturn(mock(TripUser.class));

        Assertions.assertDoesNotThrow(()->tripUserService.join(dto));
    }

    @DisplayName("회원가입시 가입된 이메일로 회원가입 하는 경우 -Service 비 정상")
    @Test
    void givenNewTripUser_whenRequesting_thenSavesNewTripUserError(){

        TripUserDto dto = createTripUserDto();
        TripUser tripUser = dto.toEntity(encoder.encode(dto.password()));
        when(tripUserRepository.findByEmail(dto.email())).thenReturn(Optional.of(tripUser));
        when(encoder.encode(dto.password())).thenReturn("encrypt_password");
        when(tripUserRepository.save(any())).thenReturn(Optional.of(tripUser));

        BoardSiteException e = Assertions.assertThrows(BoardSiteException.class,()->tripUserService.join(dto));
        Assertions.assertEquals(ErrorCode.DUPLICATED_EMAIL,e.getErrorCode());
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