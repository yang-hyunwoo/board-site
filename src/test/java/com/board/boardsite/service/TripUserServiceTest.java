package com.board.boardsite.service;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.request.user.TripUserLoginRequest;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.service.user.TripUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @DisplayName("회원 로그인 성공")
    @Test
    void givenTripEmailAndPw_whenRequesting_thenReturnToken() throws Exception {
        TripUserDto dto = createTripUserDto();

        var tripUser = dto.toEntity(dto.password());
        when(tripUserRepository.findByEmail(dto.email())).thenReturn(Optional.of(tripUser));
        when(encoder.matches(dto.password(),dto.password())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> tripUserService.login(dto.email(),dto.password()));


    }

    @DisplayName("회원 로그인 시 가입 되지 않은 이메일 입력 시")
    @Test
    void givenErrorTripEmail_whenRequesting_thenReturnException() throws Exception {
        TripUserDto dto = createTripUserDto();
        when(tripUserRepository.findByEmail(dto.email())).thenReturn(Optional.empty());


        BoardSiteException e= Assertions.assertThrows(BoardSiteException.class,()->tripUserService.login(dto.email(),dto.password()));
        Assertions.assertEquals(ErrorCode.EMAIL_NOT_FOUND,e.getErrorCode());

    }

    @DisplayName("회원 로그인 시 비밀번호가 다를 시")
    @Test
    void givenErrorTripPassword_whenRequesting_thenReturnException() throws Exception {
        TripUserDto dto = createTripUserDto();
        String passwordNot = "password";
        TripUser tripUser = dto.toEntity(encoder.encode(dto.password()));
        when(tripUserRepository.findByEmail(dto.email())).thenReturn(Optional.of(tripUser));


        BoardSiteException e = Assertions.assertThrows(BoardSiteException.class,()->tripUserService.login(dto.email(),passwordNot));
        Assertions.assertEquals(ErrorCode.INVALID_PASSWORD,e.getErrorCode());
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