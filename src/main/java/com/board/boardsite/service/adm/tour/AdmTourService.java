package com.board.boardsite.service.adm.tour;

import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.adm.tour.AdmTourRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmTourService {

    private final AdmTourRepository admTourRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public Page<TourDto> tourList(SearchTourType searchType , String searchKeyWord , Pageable pageable) {

        if (searchKeyWord == null || searchKeyWord.isBlank()) {
            return admTourRepository.findAll(pageable).map(TourDto::from);
        }
        return switch (searchType) {
            case TITLE -> admTourRepository.findByTitleContaining(searchKeyWord, pageable).map(TourDto::from);
            case CITY -> admTourRepository.findByCityContaining(searchKeyWord, pageable).map(TourDto::from);
        };
    }


    /**
     * 게시글 삭제
     * @param toruId
     * @param role
     */
    @Transactional
    public void deleteTourList(Long toruId , String role) {
        Tour tour = admTourRepository.findByIdAndDeleted(toruId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        if (role.equals("SUPER")) {
            tour.setDeleted(true);
        } else{
            throw new BoardSiteException(ErrorCode.ARTICLE_UNAUTHORIZED);
        }
    }

    /**
     * 게시글 재등록
     * @param toruId
     * @param role
     */
    @Transactional
    public void reDeleteArticleComment(Long toruId , String role) {
        Tour tour = admTourRepository.findByIdAndDeleted(toruId,true).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        if (role.equals("SUPER")) {
            tour.setDeleted(false);
        } else{
            throw new BoardSiteException(ErrorCode.ARTICLE_UNAUTHORIZED);
        }
    }

    @Transactional
    public void saveTour(TourDto dto){
        TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id()) ;
        admTourRepository.save(dto.toEntity(tripUser));
    }

    @Transactional
    public TourDto tourDetail(Long tourId , String role){
        Tour tour = admTourRepository.findById(tourId).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));

        return Optional.of(tour).map(TourDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
    }

    //관광지 수정
    @Transactional
    public void updateTour(Long tourId , TourDto dto){

        Tour tour = admTourRepository.findById(tourId).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        tour.setCity(dto.city());
        tour.setTitle(dto.title());
        tour.setContent(dto.content());
        tour.setThumbnailId(dto.thumbnailId());
    }

}
