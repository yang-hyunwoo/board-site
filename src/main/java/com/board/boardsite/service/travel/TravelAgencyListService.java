package com.board.boardsite.service.travel;

import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelAgencyListService {

    private final TravelAgencyListRepository travelAgencyListRepository;

    @Transactional(readOnly = true)
    public Page<TravelAgencyListDto> travelAgencyTripList(String travelAgencyTitleName , Pageable pageable) {

        if(travelAgencyTitleName == null || travelAgencyTitleName.isBlank()) {
            return travelAgencyListRepository.findAll(pageable).map(TravelAgencyListDto::from);
        }

        return travelAgencyListRepository.findByTitleContaining(travelAgencyTitleName,pageable).map(TravelAgencyListDto::from);
    }

    @Transactional
    public TravelAgencyListDto travelAgencyListDetail(Long travelAgencyListId){
        var travelAgencyListDetail =  travelAgencyListRepository.findByIdAndDeleted(travelAgencyListId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));

        travelAgencyListDetail.readCountPlus(travelAgencyListDetail.getReadCount());
        Optional<TravelAgencyList> optionalTravelAgencyList = Optional.of(travelAgencyListDetail);

        return optionalTravelAgencyList.map(TravelAgencyListDto::from).orElseThrow();
    }


}
