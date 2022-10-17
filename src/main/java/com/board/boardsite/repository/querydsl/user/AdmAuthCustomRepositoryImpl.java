package com.board.boardsite.repository.querydsl.user;

import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.user.QTripUser;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyCustomRepository;
import com.board.boardsite.repository.querydsl.travel.template.MySQLJPATemplates;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AdmAuthCustomRepositoryImpl extends QuerydslRepositorySupport implements AdmAuthCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AdmAuthCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TripUser.class);
        this.queryFactory = queryFactory;
    }

    QTripUser tripUser = QTripUser.tripUser;
    QTravelAgency travelAgency = QTravelAgency.travelAgency;

    @Override
    public PageImpl<AdmUserDto> findUser(String auth ,Pageable pageable) {
        var user=  queryFactory.select(Projections.bean(AdmUserDto.class,tripUser.id.as("id"),tripUser.email.as("email"),tripUser.name.as("name"),tripUser.nickName.as("nickName"),tripUser.phoneNumber.as("phoneNumber"),tripUser.deleted.as("deleted"),tripUser.emailAuth.as("emailAuth"),tripUser.role.as("role"),tripUser.travelAgencyId.as("travelAgencyId"),tripUser.authChk.as("authChk"),tripUser.profileId.as("profileId"),travelAgency.name.as("travelAgencyName")))
                .from(tripUser)
                .innerJoin(travelAgency)
                .on(tripUser.travelAgencyId.eq(travelAgency.id))
                .fetch();
        return new PageImpl<>(user,pageable,user.size());
    }

    @Override
    public PageImpl<AdmUserDto> findUserEmailContaining(String auth ,String email , Pageable pageable) {
        var user=  queryFactory.select(Projections.bean(AdmUserDto.class,tripUser.id.as("id"),tripUser.email.as("email"),tripUser.name.as("name"),tripUser.nickName.as("nickName"),tripUser.phoneNumber.as("phoneNumber"),tripUser.deleted.as("deleted"),tripUser.emailAuth.as("emailAuth"),tripUser.role.as("role"),tripUser.travelAgencyId.as("travelAgencyId"),tripUser.authChk.as("authChk"),tripUser.profileId.as("profileId"),travelAgency.name.as("travelAgencyName")))
                .from(tripUser)
                .innerJoin(travelAgency)
                .on(tripUser.travelAgencyId.eq(travelAgency.id))
                .where(tripUser.email.contains(email))
                .fetch();
        return new PageImpl<>(user,pageable,user.size());
    }



}
