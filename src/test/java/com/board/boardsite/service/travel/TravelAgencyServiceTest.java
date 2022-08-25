package com.board.boardsite.service.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("여행사 페이지")
@SpringBootTest
class TravelAgencyServiceTest {

    @Autowired
    private TravelAgencyService travelAgencyService;

    @MockBean
    private TravelAgencyRepository travelAgencyRepository;

//    @MockBean

    @DisplayName("[GET][service] 여행사 리스트 조회 (여행사 검색 포함) ")
    @Test
    void givenNothing_whenTravelAgency_thenReturnTravelAgency() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        given(travelAgencyRepository.findAllByDeleted(pageable,false)).willReturn(Page.empty());

        // When & Then
        Page<TravelAgencyDto> travelAgencies = travelAgencyService.travelAgencyList(null , pageable);
        assertThat(travelAgencies.isEmpty());
        then(travelAgencyRepository).should().findAllByDeleted(pageable,false);

    }

    @DisplayName("[GET][service] 여행사 상세 조회 (여행 리스트 제외) ")
    @Test
    void givenTravelAgencyId_whenTravelAgencyDetail_thenReturnTravelAgencyDetail() {
        // Given
        long travelAgencyId = 1L;
        TravelAgency travelAgency = createTravelAgency();
        given(travelAgencyRepository.findByIdAndDeleted(travelAgencyId,false)).willReturn(Optional.of(travelAgency));

        // When & Then
        var dto = travelAgencyService.travelAgencyDetail(travelAgencyId);
        assertThat(dto)
                .hasFieldOrPropertyWithValue("name",travelAgency.getName());
        then(travelAgencyRepository).should().findByIdAndDeleted(travelAgencyId,false);

    }

    @DisplayName("[GET][service] 여행사 상세 조회 (여행 리스트 포함)")
    @Test
    void givenTravelAgencyId_whenTravelAgencyDetail_thenReturnTravelAgencyWithTravelAgencyLiST() {
        // Given
        long travelAgencyId = 1L;
        TravelAgency travelAgency = createTravelAgency();
        given(travelAgencyRepository.findByIdAndDeleted(travelAgencyId,false)).willReturn(Optional.of(travelAgency));

        // When & Then
        var dto = travelAgencyService.travelAgencyWithTravelAgencyList(travelAgencyId);
        assertThat(dto)
                .hasFieldOrPropertyWithValue("name",travelAgency.getName());
        then(travelAgencyRepository).should().findByIdAndDeleted(travelAgencyId,false);

    }

    @DisplayName("[GET][service] 여행사 랜덤 3개 쿼리")
    @Test
    void givenNothing_whenTravelAgency_thenReturnTravelAgencyListThree() {

        TravelAgency travelAgency = createTravelAgency();

        List<TravelAgencyDto> list = travelAgencyService.travelAgencyRandomThree();
        assertThat(list)
                .hasSize(0);
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