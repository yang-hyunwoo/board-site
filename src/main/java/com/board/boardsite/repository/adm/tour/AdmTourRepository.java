package com.board.boardsite.repository.adm.tour;

import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.repository.querydsl.adm.tour.AdmTourCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Optional;

public interface AdmTourRepository extends
        JpaRepository<Tour, Long>,
        QuerydslPredicateExecutor<Tour>,
        AdmTourCustomRepository
{

    Page<Tour> findByTitleContaining(String title , Pageable pageable);

    Page<Tour> findByCityContaining(String city , Pageable pageable);

    Optional<Tour> findByIdAndDeleted(Long tourId , boolean deleted);


}