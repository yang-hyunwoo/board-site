package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TravelAgencyListCustomRepository {

    List<TravelAgencyListOnlyListDto> findByDeletedAndSortIsNotNullOrderBySort();

    PageImpl<TravelAgencyListOnlyListDto> findByCustomTravelAgency_IdAndDeleted(Long id, boolean deleted, Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByAllDeleted( boolean deleted, Pageable pageable);

    PageImpl<TravelAgencyListOnlyListDto> findCustomByTitleContaingAndDeleted(String travelAgencyTitleName , boolean deleted, Pageable pageable);

    Optional<TravelAgencyListOnlyListDto> findCustomByDetail(Long ListId, boolean deleted);

}


