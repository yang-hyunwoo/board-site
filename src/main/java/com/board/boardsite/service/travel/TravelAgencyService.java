package com.board.boardsite.service.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.travel.TravelAgencyWithTravelAgencyListDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class TravelAgencyService {

    private final TravelAgencyRepository travelAgencyRepository;

    private final TravelAgencyListRepository travelAgencyListRepository;

    //travelAgencyRepository.findByNameContaining(travelAgencyName , pageable) : travelAgency (entity) 여서 .map을 통해 dto에 담아준다.
    @Transactional(readOnly = true)
    public Page<TravelAgencyDto> travelAgencyList(String travelAgencyName , Pageable pageable) {

        if (travelAgencyName == null || travelAgencyName.isBlank()) {
            return travelAgencyRepository.findAll(pageable).map(TravelAgencyDto::from);
        }
        return travelAgencyRepository.findByNameContaining(travelAgencyName , pageable).map(TravelAgencyDto::from);
    }

    @Transactional(readOnly = true)
    public TravelAgencyDto travelAgencyDetail(Long travelAgencyId) {
        return travelAgencyRepository.findById(travelAgencyId).map(TravelAgencyDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public TravelAgencyWithTravelAgencyListDto travelAgencyWithTravelAgencyList(Long travelAgencyId) {
        TravelAgency travelAgency = travelAgencyRepository.findById(travelAgencyId).orElseThrow();
        List<TravelAgencyList> travelAgencyList = travelAgencyListRepository.findByTravelAgencyIdAndDeletedOrderByTitleAsc(travelAgencyId,false);
        System.out.println("bb::"+travelAgencyList);
        travelAgency.getTravelAgencyLists().clear();
        travelAgency.getTravelAgencyLists().addAll(travelAgencyList);
        System.out.println("aa::"+travelAgency.getTravelAgencyLists());
        Optional<TravelAgency> optionalTravelAgency = Optional.of(travelAgency);
        return optionalTravelAgency.map(TravelAgencyWithTravelAgencyListDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
    }

}
