package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.travel.QTravelAgencyReservation;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationOnlyListDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TravelAgencyReservationCustomRepositoryImpl extends QuerydslRepositorySupport implements TravelAgencyReservationCustomRepository {
    private final JPAQueryFactory queryFactory;

    public TravelAgencyReservationCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgencyReservation.class);
        this.queryFactory = queryFactory;
    }



    QTravelAgencyReservation reservation = QTravelAgencyReservation.travelAgencyReservation;
    QAttachFile attachFile = QAttachFile.attachFile;
    QAttachFile achfile = new QAttachFile("achfile");
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

    @Override
    public PageImpl<TravelAgencyReservationOnlyListDto> findCustomList(Long id, Pageable pageable) {
        var reservationDto =  queryFactory.select(
                            Projections.bean(TravelAgencyReservationOnlyListDto.class,
                                    ExpressionUtils.as(
                                            JPAExpressions.select(achfile.filePath)
                                                    .from(achfile)
                                                    .where(achfile.fileId.eq(reservation.travelAgencyList.thumnbnailFileId)),"thumbPath"),
                                    ExpressionUtils.as(
                                            JPAExpressions.select(achfile.filePath)
                                                    .from(achfile)
                                                    .where(achfile.fileId.eq(reservation.qrCodeId)),"filePath"),
                            reservation.id.as("id"),
                            reservation.travelAgency.id.as("travelAgencyId"),
                            reservation.travelAgencyList.id.as("travelAgencyListId"),
                            reservation.tripUser.as("tripUser"),
                            reservation.merchantUid.as("merchantUid"),
                            reservation.impUid.as("impUid"),
                            reservation.payEmail.as("payEmail"),
                            reservation.payName.as("payName"),
                            reservation.paid.as("paid"),
                            reservation.realPaid.as("realPaid"),
                            reservation.personCount.as("personCount"),
                            reservation.salePercent.as("salePercent"),
                            reservation.deleted.as("deleted"),
                            reservation.qrCodeId.as("qrCodeId"),
                            reservation.qrChk.as("qrChk"),
                            reservation.travelAgency.as("travelAgencyDto"),
                            reservation.travelAgencyList.as("travelAgencyListDto"),
                            reservation.createdAt.as("createdAt")))
                .from(reservation)
                .where(reservation.tripUser.id.eq(id))
                .orderBy(reservation.createdAt.desc())
                .fetch();

        return new PageImpl<>(reservationDto, pageable, reservationDto.size());

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
