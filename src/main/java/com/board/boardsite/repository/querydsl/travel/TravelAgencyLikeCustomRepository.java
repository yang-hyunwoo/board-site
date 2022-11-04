package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.dto.travel.TravelAgencyLikeOnlyDto;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelAgencyLikeCustomRepository {

    PageImpl<TravelAgencyLikeOnlyDto> findByCustomTripUser_Id(Long id, Pageable pageable);

}
