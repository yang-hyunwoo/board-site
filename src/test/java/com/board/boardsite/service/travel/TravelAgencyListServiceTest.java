package com.board.boardsite.service.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("여행 리스트 목록")
@SpringBootTest
class TravelAgencyListServiceTest {

    @Autowired
    private TravelAgencyListService travelAgencyListService;

    @MockBean
    private TravelAgencyListRepository travelAgencyListRepository;

    @DisplayName("[GET][service] 여행 목록 리스트 조회")
    @Test
    void givenNothing_whenTravelAgencyList_thenReturnTravelAgencyList() {
        // Given
        Pageable pageable = Pageable.ofSize(9);
        given(travelAgencyListRepository.findAllByDeleted(pageable,false)).willReturn(Page.empty());
        // When & Then
        Page<TravelAgencyListDto> travelAgencyListDtos = travelAgencyListService.travelAgencyTripList(null,pageable);
        assertThat(travelAgencyListDtos.isEmpty());
        then(travelAgencyListRepository).should().findAllByDeleted(pageable,false);
    }

    @DisplayName("[GET][service] 여행 상세 조회")
    @Test
    void givenTravelAgencyListId_whenTravelAgencyDetail_thenReturnTravelAgencyDetail() {
        long travelAgencyListId = 1L;
        TravelAgencyList travelAgencyList = createdTravelAgency2();
        given(travelAgencyListRepository.findByIdAndDeleted(travelAgencyListId,false)).willReturn(Optional.of(travelAgencyList));

        var dto = travelAgencyListService.travelAgencyListDetail(travelAgencyListId);

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