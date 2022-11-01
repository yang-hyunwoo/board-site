package com.board.boardsite.repository.querydsl.tour;

import com.board.boardsite.domain.tour.QTour;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.repository.querydsl.travel.template.MySQLJPATemplates;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TourCustomRepositoryImpl extends QuerydslRepositorySupport implements TourCustomRepository {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public TourCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Tour.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public List<Tour> findTourRandomCount(int count) {
        JPAQuery<Tour> query = new JPAQuery<>(em , MySQLJPATemplates.DEFAULT);
        QTour tour = QTour.tour;
        return query.from(tour)
                .where(tour.deleted.eq(false))
                .groupBy(tour.id)
                .orderBy(NumberExpression.random().asc())
                .limit(count)
                .fetch();
    }
}
