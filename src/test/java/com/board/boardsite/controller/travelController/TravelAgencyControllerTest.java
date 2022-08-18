package com.board.boardsite.controller.travelController;

import com.board.boardsite.config.TestSecurityConfig;
import com.board.boardsite.service.travel.TravelAgencyService;
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
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("컨트롤러 - 여행사")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class TravelAgencyControllerTest {

    @Autowired
    private final MockMvc mvc;

    @MockBean
    private TravelAgencyService travelAgencyService;

    public TravelAgencyControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET][Controller] 여행사 리스트 조회")
    @Test
    public void givenNothing_whenRequestingTravelAgencyList_thenReturnTravelAgencyList() throws Exception {

        // Given
        given(travelAgencyService.travelAgencyList(eq(null),any(Pageable.class))).willReturn(Page.empty());
        // When & Then
        mvc.perform(get("/api/trip/agency/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        then(travelAgencyService).should().travelAgencyList(eq(null), any(Pageable.class));
    }

}