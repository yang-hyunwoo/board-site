package com.board.boardsite.service.travel;

import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.travel.TravelAgencyLikeDto;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.repository.travel.TravelAgencyListLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelAgencyListLikeService {

    private final TravelAgencyListLikeRepository travelAgencyListLikeRepository;

    @Transactional(readOnly = true)
    public Page<TravelAgencyLikeDto> travelAgencyLikeList(Long id , Pageable pageable) {
       var travelAgencyLikeDto =  travelAgencyListLikeRepository.findByTripUser_Id(id, pageable).map(TravelAgencyLikeDto::from);;

       return travelAgencyLikeDto;
    }
}
