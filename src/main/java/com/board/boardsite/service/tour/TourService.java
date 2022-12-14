package com.board.boardsite.service.tour;


import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.tour.TourRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

    private final TripUserRepository tripUserRepository;

//
@Transactional(readOnly = true)
public Page<TourOnlyListDto> tourSearchList(SearchTourType searchType , String searchKeyWord , Pageable pageable) {
    if (searchKeyWord == null || searchKeyWord.isBlank()) {
        return tourRepository.findCustomAllByDeleted(false, pageable);
    }
    return switch (searchType) {
        case TITLE -> tourRepository.findCustomByTitleContainingAndDeleted(searchKeyWord,false, pageable);
        case CITY -> tourRepository.findCustomByCityContainingAndDeleted(searchKeyWord,false,pageable );
    };
}
    //수정
    @Transactional
    public TourDto tourDetail(Long tourId, String role) {
        Tour tourDetail;

        if(role==null) {
            tourDetail = tourRepository.findById(tourId).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        } else {
            if (role.equals("USER")) {
                tourDetail = tourRepository.findByIdAndDeleted(tourId, false).orElseThrow(() -> new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
                tourDetail.readCountPlus(tourDetail.getReadCount());
            } else {
                tourDetail = tourRepository.findById(tourId).orElseThrow(() -> new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
            }
        }
        return TourDto.from(tourDetail);
    }

    @Transactional(readOnly = true)
    public List<TourOnlyListDto> tourRandomThree() {
        int count = 3;
        return tourRepository.findTourRandomCount(count);
    }



}
