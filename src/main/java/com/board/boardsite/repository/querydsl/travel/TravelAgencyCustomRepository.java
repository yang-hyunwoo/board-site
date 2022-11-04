package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;

import java.util.List;

public interface TravelAgencyCustomRepository {

    List<TravelAgencyOnlyListDto> findTravelAgencyRandomCount(int count);
}
