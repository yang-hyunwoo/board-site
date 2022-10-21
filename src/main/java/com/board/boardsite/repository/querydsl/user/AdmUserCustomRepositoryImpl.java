package com.board.boardsite.repository.querydsl.user;

import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.QTravelAgencyReservation;
import com.board.boardsite.domain.user.QTripUser;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AdmUserCustomRepositoryImpl extends QuerydslRepositorySupport implements AdmUserCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AdmUserCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TripUser.class);
        this.queryFactory = queryFactory;
    }

    QTripUser tripUser = QTripUser.tripUser;
    QTravelAgencyReservation travelAgencyReservation = QTravelAgencyReservation.travelAgencyReservation;


    @Override
    public PageImpl<AdmUserDto> findTravelAgencyIdAndUser(Long travelAgencyId, Pageable pageable) {
        var user = queryFactory.select(Projections.bean(AdmUserDto.class,
                        tripUser.id.as("id"),
                        tripUser.email.as("email"),
                        tripUser.name.as("name"),
                        tripUser.nickName.as("nickName"),
                        tripUser.phoneNumber.as("phoneNumber"),
                        tripUser.deleted.as("deleted"),
                        tripUser.emailAuth.as("emailAuth"),
                        tripUser.role.as("role"),
                        tripUser.travelAgencyId.as("travelAgencyId"),
                        tripUser.authChk.as("authChk"),
                        tripUser.profileId.as("profileId")))
                .from(tripUser)
                .innerJoin(travelAgencyReservation)
                .on(tripUser.id.eq(travelAgencyReservation.tripUser.id))
                .where(travelAgencyReservation.travelAgency.id.eq(travelAgencyId))
                .where(tripUser.role.eq("USER"))
                .groupBy(tripUser.id)
                .fetch();
        return new PageImpl<>(user, pageable, user.size());
    }

    @Override
    public PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndName(Long travelAgencyId, String name, Pageable pageable) {
        var user = queryFactory.select(Projections.bean(AdmUserDto.class,
                        tripUser.id.as("id"),
                        tripUser.email.as("email"),
                        tripUser.name.as("name"),
                        tripUser.nickName.as("nickName"),
                        tripUser.phoneNumber.as("phoneNumber"),
                        tripUser.deleted.as("deleted"),
                        tripUser.emailAuth.as("emailAuth"),
                        tripUser.role.as("role"),
                        tripUser.travelAgencyId.as("travelAgencyId"),
                        tripUser.authChk.as("authChk"),
                        tripUser.profileId.as("profileId")))
                .from(tripUser)
                .innerJoin(travelAgencyReservation)
                .on(tripUser.id.eq(travelAgencyReservation.tripUser.id))
                .where(travelAgencyReservation.travelAgency.id.eq(travelAgencyId))
                .where(tripUser.role.eq("USER"))
                .where(tripUser.name.contains(name))
                .groupBy(tripUser.id)
                .fetch();
        return new PageImpl<>(user, pageable, user.size());
    }

    @Override
    public PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndEmail(Long travelAgencyId, String email, Pageable pageable) {
        var user = queryFactory.select(Projections.bean(AdmUserDto.class,
                        tripUser.id.as("id"),
                        tripUser.email.as("email"),
                        tripUser.name.as("name"),
                        tripUser.nickName.as("nickName"),
                        tripUser.phoneNumber.as("phoneNumber"),
                        tripUser.deleted.as("deleted"),
                        tripUser.emailAuth.as("emailAuth"),
                        tripUser.role.as("role"),
                        tripUser.travelAgencyId.as("travelAgencyId"),
                        tripUser.authChk.as("authChk"),
                        tripUser.profileId.as("profileId")))
                .from(tripUser)
                .innerJoin(travelAgencyReservation)
                .on(tripUser.id.eq(travelAgencyReservation.tripUser.id))
                .where(travelAgencyReservation.travelAgency.id.eq(travelAgencyId))
                .where(tripUser.role.eq("USER"))
                .where(tripUser.email.contains(email))
                .groupBy(tripUser.id)
                .fetch();
        return new PageImpl<>(user, pageable, user.size());
    }

    @Override
    public PageImpl<AdmUserDto> findTravelAgencyIdAndUserAndNickName(Long travelAgencyId, String nickName, Pageable pageable) {
        var user = queryFactory.select(Projections.bean(AdmUserDto.class,
                        tripUser.id.as("id"),
                        tripUser.email.as("email"),
                        tripUser.name.as("name"),
                        tripUser.nickName.as("nickName"),
                        tripUser.phoneNumber.as("phoneNumber"),
                        tripUser.deleted.as("deleted"),
                        tripUser.emailAuth.as("emailAuth"),
                        tripUser.role.as("role"),
                        tripUser.travelAgencyId.as("travelAgencyId"),
                        tripUser.authChk.as("authChk"),
                        tripUser.profileId.as("profileId")))
                .from(tripUser)
                .innerJoin(travelAgencyReservation)
                .on(tripUser.id.eq(travelAgencyReservation.tripUser.id))
                .where(travelAgencyReservation.travelAgency.id.eq(travelAgencyId))
                .where(tripUser.role.eq("USER"))
                .where(tripUser.nickName.contains(nickName))
                .groupBy(tripUser.id)
                .fetch();
        return new PageImpl<>(user, pageable, user.size());
    }
}
