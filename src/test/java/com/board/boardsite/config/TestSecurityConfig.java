package com.board.boardsite.config;


import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.repository.user.TripUserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private TripUserRepository tripUserRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(tripUserRepository.findByEmail(anyString())).willReturn(Optional.of(TripUser.of(
                "gusdnTest",
                "pw",
                "gusdn-test@email.com" ,
                "test",
                "010",
                0,
                Gender.M,
                false,
                null,
                false,
                "USER",
                null,
                null,
                false
        )));
    }

}
