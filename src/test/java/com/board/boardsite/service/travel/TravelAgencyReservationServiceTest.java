package com.board.boardsite.service.travel;

import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import com.board.boardsite.repository.travel.TravelAgencyReservationRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("여행 리스트 결제")
@SpringBootTest
class TravelAgencyReservationServiceTest {

    @Autowired
    private TravelAgencyReservationService travelAgencyReservationService;

    @MockBean
    private TravelAgencyReservationRepository travelAgencyReservationRepository;

    @Autowired
    private TravelAgencyService travelAgencyService;

    @MockBean
    private TravelAgencyRepository travelAgencyRepository;

    @MockBean
    private TravelAgencyListRepository travelAgencyListRepository;

    @MockBean
    private TripUserRepository tripUserRepository;

    @DisplayName("[POST][service] 아임 포트 결제 완료 시 여행 결제 테이블 저장")
    @Test
    void giveTravelAgencyPay_whenRequestTravelAgencyPay_thenReturnTravelAgencyPay() {
        TravelAgencyReservationDto dto = createdTravelAgencyReservationDto();
        given(travelAgencyRepository.findByIdAndDeleted(dto.travelAgencyId(),false)).willReturn(Optional.of(createTravelAgency()));
        given(tripUserRepository.findById(dto.tripUser().id())).willReturn(Optional.of(createTripUser()));
        given(travelAgencyReservationRepository.save(any(TravelAgencyReservation.class))).willReturn(null);

        travelAgencyReservationService.travelAgencyReserSave(dto);

        then(travelAgencyRepository).should().findByIdAndDeleted(dto.travelAgencyId(),false);
        then(tripUserRepository).should().findById(dto.tripUser().id());

    }

    private TravelAgencyReservationDto createdTravelAgencyReservationDto(){
        return TravelAgencyReservationDto.of(
                1L,
                1L,
                1L,
                createTripUserDto(),
                "1",
                "2",
                "3@naver.com",
                "aa",
                400,
                400,
                1,
                0,
                false
        );
    }

    private TripUser createTripUser() {
        return TripUser.of(
                1L,
                "gus",
                "gus",
                "gus5162@naver.com",
                "test",
                "010",
                0,
                Gender.M,
                false,
                false
        );
    }

    private TripUserDto createTripUserDto() {
        return TripUserDto.of(
                1L,
                "gus5162@naver.com",
                "gus",
                "gus",
                "0102",
                0,
                false,
                "test",
                Gender.M,
                false,
                LocalDateTime.now(),
                null,
                LocalDateTime.now(),
                null

        );
    }

    private TravelAgency createTravelAgency() {
        TravelAgency travelAgency = TravelAgency.of(
                "해양",
                "서울특별시 ㅇ1ㅇ1",
                "02-1234-5678",
                "최선을 다하자.",
                false
        );
        return travelAgency;
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
                createTravelAgency()
        );
        return travelAgencyList;
    }
}