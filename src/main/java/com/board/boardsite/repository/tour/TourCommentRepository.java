package com.board.boardsite.repository.tour;

import com.board.boardsite.domain.tour.TourComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface TourCommentRepository extends JpaRepository<TourComment, Long> {

    List<TourComment> findByTour_IdAndDeleted(Long tourId,boolean deleted);

    Page<TourComment> findByTour_IdAndDeleted(Long toruId, boolean deleted, Pageable pageable);

    Optional<TourComment> findByIdAndDeleted(Long tourId , boolean deleted);
    void deleteByIdAndTripUser_Id(Long tourCommentId , Long id);
}
