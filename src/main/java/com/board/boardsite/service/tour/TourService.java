package com.board.boardsite.service.tour;


import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.tour.TourRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

    private final TripUserRepository tripUserRepository;

//
@Transactional(readOnly = true)
public Page<TourDto> tourSearchList(SearchTourType searchType , String searchKeyWord , Pageable pageable) {

    if (searchKeyWord == null || searchKeyWord.isBlank()) {
        return tourRepository.findAllByDeleted(pageable,false).map(TourDto::from);
    }
    return switch (searchType) {
        case TITLE -> tourRepository.findByTitleContainingAndDeleted(searchKeyWord, pageable,false).map(TourDto::from);
        case CITY -> tourRepository.findByCityContainingAndDeleted(searchKeyWord, pageable , false).map(TourDto::from);
    };
}
    //수정
    @Transactional
    public TourDto tourDetail(Long tourId) {
        var tourDetail = tourRepository.findByIdAndDeleted(tourId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        tourDetail.readCountPlus(tourDetail.getReadCount());
        return TourDto.from(tourDetail);
    }

    @Transactional(readOnly = true)
    public List<TourDto> tourRandomThree() {
        int count = 3;
        return tourRepository.findTourRandomCount(count).stream().map(TourDto::from).collect(Collectors.toList());
    }

}