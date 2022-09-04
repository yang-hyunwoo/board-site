package com.board.boardsite.repository.tour;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.QArticle;
import com.board.boardsite.domain.tour.QTour;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.repository.querydsl.tour.TourCustomRepository;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
