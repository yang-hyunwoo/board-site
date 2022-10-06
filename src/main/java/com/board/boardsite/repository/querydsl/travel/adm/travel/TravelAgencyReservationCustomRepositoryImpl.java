package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.domain.travel.QTravelAgencyReservation;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class TravelAgencyReservationCustomRepositoryImpl extends QuerydslRepositorySupport implements TravelAgencyReservationCustomRepository {
    private final JPAQueryFactory queryFactory;

    public TravelAgencyReservationCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgencyReservation.class);
        this.queryFactory = queryFactory;
    }



    QTravelAgencyReservation reservation = QTravelAgencyReservation.travelAgencyReservation;
    @Override
    public List<TravelListCountDto> findTravelAgencyReservation(Long travelAgencyId,String auth) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String formatedNow = now.format(formatter);

        String startDay = formatedNow+"01";
        String endDay = formatedNow+"31";
        if(auth.equals("ADMIN")) {
            return queryFactory.select(Projections.bean(TravelListCountDto.class, reservation.count().as("count"), formattedDate.as("date")))
                    .from(reservation)
                    .where(reservation.deleted.eq(false),
                            reservation.travelAgency.id.eq(travelAgencyId),
                            formattedDate2.between(startDay, endDay)
                    ).groupBy(formattedDate)
                    .orderBy(formattedDate.asc())
                    .fetch();
        } else {
            return queryFactory.select(Projections.bean(TravelListCountDto.class, reservation.count().as("count"), formattedDate.as("date")))
                    .from(reservation)
                    .where(reservation.deleted.eq(false),
                            formattedDate2.between(startDay, endDay)
                    ).groupBy(formattedDate)
                    .orderBy(formattedDate.asc())
                    .fetch();
        }

    }


    StringTemplate formattedDate = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})"
            , reservation.createdAt
            , ConstantImpl.create("%Y-%m-%d"));

    StringTemplate formattedDate2 = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})"
            , reservation.createdAt
            , ConstantImpl.create("%Y%m%d"));
}
