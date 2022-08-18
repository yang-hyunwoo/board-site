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
        given(travelAgencyRepository.findAll(pageable)).willReturn(Page.empty());

        // When & Then
        Page<TravelAgencyDto> travelAgencies = travelAgencyService.travelAgencyList(null , pageable);
        assertThat(travelAgencies.isEmpty());
        then(travelAgencyRepository).should().findAll(pageable);

    }

}