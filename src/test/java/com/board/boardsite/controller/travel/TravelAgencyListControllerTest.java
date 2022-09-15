package com.board.boardsite.controller.travel;

import com.board.boardsite.config.TestSecurityConfig;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.service.travel.TravelAgencyListService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("컨트롤러 -여행 리스트")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class TravelAgencyListControllerTest {

    @Autowired
    private final MockMvc mvc;

    @MockBean
    private TravelAgencyListService travelAgencyListService;

    public TravelAgencyListControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET][controller] 여행 리스트 조회")
    @Test
    void givenNothing_whenRequestingTravelAgencyList_thenReturnTravelAgencyList() throws Exception {

        // Given
        given(travelAgencyListService.travelAgencyTripList(eq(null), any(Pageable.class))).willReturn(Page.empty());
        // When & Then
        mvc.perform(get("/api/trip/agency-trip/triplist")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        then(travelAgencyListService).should().travelAgencyTripList(eq(null), any(Pageable.class));
    }

    @DisplayName("[GET][controller] 여행 리스트 상세 조회")
    @Test
    void givenTravelAgencyListId_whenRequestingTravelAgencyDetail_thenReturnTravelAgencyDetail() throws Exception {
        long travelAgencyId = 1L;
        TravelAgencyList travelAgencyList = createdTravelAgency2();
        given(travelAgencyListService.travelAgencyListDetail(travelAgencyId)).willReturn(TravelAgencyListDto.from(travelAgencyList));

        mvc.perform(get("/api/trip/agency-trip/"+travelAgencyId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        then(travelAgencyListService).should().travelAgencyListDetail(travelAgencyId);
    }

    private TravelAgencyList createdTravelAgency2() {
        TravelAgencyList travelAgencyList = TravelAgencyList.of(
                "seoul",
                "20220818",
                "20221010",
                "어서오세유",
                "반갑슈",
                100,
                20,
                80,
                0,
                0,
                0,
                null,
                null,
                createTravelAgency()
        );
        return travelAgencyList;
    }
    private TravelAgency createTravelAgency() {
        TravelAgency travelAgency = TravelAgency.of(
                "해양",
                "서울특별시 ㅇ1ㅇ1",
                "02-1234-5678",
                "최선을 다하자.",
                null,
                null,
                false
        );
        return travelAgency;
    }
}