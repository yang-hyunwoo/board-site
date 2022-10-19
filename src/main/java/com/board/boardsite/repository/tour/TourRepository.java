package com.board.boardsite.repository.tour;

import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.repository.querydsl.tour.TourCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Optional;

public interface TourRepository extends
        JpaRepository<Tour, Long>,
        QuerydslPredicateExecutor<Tour>,
        TourCustomRepository
{

            Page<Tour> findByTitleContainingAndDeleted(String title , Pageable pageable,boolean deleted);

            Optional<Tour> findByIdAndDeleted(Long tourId , boolean deleted);

            Page<Tour> findAllByDeleted(Pageable pageable , boolean deleted);
            Page<Tour> findByCityContainingAndDeleted(String city , Pageable pageable,boolean deleted);

        }
