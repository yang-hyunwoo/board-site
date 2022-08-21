package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.travel.TravelAgency;

import java.util.List;
import java.util.Optional;

public interface TravelAgencyCustomRespository {

    List<TravelAgency> findTravelAgencyRandomCount(int count);
}
