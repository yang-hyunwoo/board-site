package com.board.boardsite.service.tour;

import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.tour.TourComment;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.tour.TourCommentDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.tour.TourCommentRepository;
import com.board.boardsite.repository.tour.TourRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourCommentService {

    private final TourCommentRepository tourCommentRepository;

    private final TourRepository tourRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public List<TourCommentDto> searchTourComments(Long articleId) {
        return tourCommentRepository.findByTour_IdAndDeleted(articleId,false)
                .stream().map(TourCommentDto::from).toList();
    }

    @Transactional(readOnly = true)
    public Page<TourCommentDto> searchTourCommentsPage(Long articleId,Pageable pageable) {
        return tourCommentRepository.findByTour_IdAndDeleted(articleId,false,pageable).map(TourCommentDto::from);
    }

    @Transactional
    public void saveTourComment(TourCommentDto dto) {
        try {
            Tour tour = tourRepository.findByIdAndDeleted(dto.tourId(),false).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
            TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());
            tourCommentRepository.save(dto.toEntity(tour, tripUser));
        } catch (Exception e) {
            throw new BoardSiteException(ErrorCode.EMAIL_NOT_FOUND);
        }
    }

    @Transactional
    public void updateTourComment(Long tourCommentId , TourCommentDto dto){

        TourComment tourComment = tourCommentRepository.findByIdAndDeleted(tourCommentId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
        TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());
        if(tourComment.getTripUser().getId().equals(tripUser.getId())) {
            if (dto.content() != null) {
                tourComment.setContent(dto.content());
                }
        } else {
            throw  new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
        }

    }

    @Transactional
    public void deleteTourComment(Long tourCommentId , Long userId) {
            TourComment tourComment = tourCommentRepository.findByIdAndDeleted(tourCommentId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TOUR_NOT_FOUND));
            TripUser tripUser = tripUserRepository.getReferenceById(userId);

            if (tourComment.getTripUser().getId().equals(tripUser.getId())) {
                tourComment.setDeleted(true);
            } else{
                throw new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
            }
    }
}
