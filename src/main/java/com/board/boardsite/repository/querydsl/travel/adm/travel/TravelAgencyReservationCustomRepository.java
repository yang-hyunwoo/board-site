package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.querydsl.core.Tuple;

import java.util.List;

public interface TravelAgencyReservationCustomRepository {

    List<TravelListCountDto> findTravelAgencyReservation(Long travelAgencyId , String auth);
}
