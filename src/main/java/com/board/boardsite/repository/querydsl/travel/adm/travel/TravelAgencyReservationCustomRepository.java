package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.travel.TravelAgencyLikeOnlyDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationOnlyListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelAgencyReservationCustomRepository {

    List<TravelListCountDto> findTravelAgencyReservation(Long travelAgencyId , String auth);

    PageImpl<TravelAgencyReservationOnlyListDto> findCustomList(Long id, Pageable pageable);
}
