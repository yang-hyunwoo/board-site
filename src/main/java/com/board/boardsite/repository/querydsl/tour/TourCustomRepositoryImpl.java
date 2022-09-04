package com.board.boardsite.repository.querydsl.tour;

import com.board.boardsite.domain.tour.QTour;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyCustomRepository;
import com.board.boardsite.repository.querydsl.travel.template.MySQLJPATemplates;
import com.board.boardsite.repository.tour.TourRepository;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TourCustomRepositoryImpl extends QuerydslRepositorySupport implements TourCustomRepository {
    @PersistenceContext
    EntityManager em;
    public TourCustomRepositoryImpl() {
        super(Tour.class);
    }


    @Override
    public List<Tour> findTourRandomCount(int count) {
        JPAQuery<Tour> query = new JPAQuery<>(em , MySQLJPATemplates.DEFAULT);
        QTour tour = QTour.tour;
        return query.from(tour)
                .distinct()
                .where(tour.deleted.eq(false))
                .orderBy(NumberExpression.random().asc())
                .limit(count)
                .fetch();
    }
}
