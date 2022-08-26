package com.board.boardsite.controller.travel;

import com.board.boardsite.config.TestSecurityConfig;
import com.board.boardsite.dto.request.travel.TravelAgencyReservationRequest;
import com.board.boardsite.service.travel.TravelAgencyReservationService;
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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("아임포트 결제")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class TravelAgencyReservationControllerTest {

    @Autowired
    private final MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TravelAgencyReservationService travelAgencyReservationService;

    public TravelAgencyReservationControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[POST][controller] 아임 포트 결제 완료 시 여행 결제 테이블 저장")
    @Test
    @WithUserDetails(value = "gusdnTest", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    void giveTravelAgencyPay_whenRequestTravelAgencyPay_thenReturnTravelAgencyPay() throws Exception{
        TravelAgencyReservationRequest travelAgencyReservationRequest = TravelAgencyReservationRequest.of(1L,
                1L,
                "11",
                "22",
                "33",
                "44",
                100,
                200) ;


        mvc.perform(post("/api/trip/reser/pay/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(travelAgencyReservationRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[POST][controller] 구매 내역 리스트 조회")
    @Test
    @WithUserDetails(value = "gusdnTest", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    void giveTravelUserId_whenRequestTravelAgencyPay_thenReturnTravelAgencyReservation() throws Exception{

        given(travelAgencyReservationService.getReservationList(eq(null),any(Pageable.class))).willReturn(Page.empty());
        // When & Then
        mvc.perform(get("/api/trip/reser/purchaseList")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        then(travelAgencyReservationService).should().getReservationList(eq(null), any(Pageable.class));

    }

}