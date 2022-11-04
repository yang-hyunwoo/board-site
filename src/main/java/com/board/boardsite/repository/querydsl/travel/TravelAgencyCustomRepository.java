package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelAgencyCustomRepository {

    List<TravelAgencyOnlyListDto> findTravelAgencyRandomCount(int count);

    PageImpl<TravelAgencyOnlyListDto> findCustomAllByDeleted(boolean deleted , Pageable pageable);

    PageImpl<TravelAgencyOnlyListDto> findCustomByNameContainingAndDeleted(String travelAgencyName , boolean deleted , Pageable pageable);

}
