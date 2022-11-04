package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;

import java.util.List;

public interface TravelAgencyListCustomRepository {

    List<TravelAgencyListOnlyListDto> findByDeletedAndSortIsNotNullOrderBySort();
}
