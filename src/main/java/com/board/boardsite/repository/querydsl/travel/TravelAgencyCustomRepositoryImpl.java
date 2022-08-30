package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.repository.querydsl.travel.template.MySQLJPATemplates;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TravelAgencyCustomRepositoryImpl extends QuerydslRepositorySupport implements TravelAgencyCustomRepository {
    @PersistenceContext
    EntityManager em;
    public TravelAgencyCustomRepositoryImpl() {
        super(TravelAgency.class);
    }


    @Override
    public List<TravelAgency> findTravelAgencyRandomCount(int count) {
        JPAQuery<TravelAgency> query = new JPAQuery<>(em , MySQLJPATemplates.DEFAULT);
        QTravelAgency travelAgency = QTravelAgency.travelAgency;
        return query.from(travelAgency)
                .distinct()
                .where(travelAgency.deleted.eq(false))
                .orderBy(NumberExpression.random().asc())
                .limit(count)
                .fetch();
    }
}
