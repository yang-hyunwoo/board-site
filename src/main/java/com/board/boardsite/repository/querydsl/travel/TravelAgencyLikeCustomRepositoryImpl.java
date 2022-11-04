package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.QTravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.dto.travel.TravelAgencyLikeOnlyDto;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.board.boardsite.repository.querydsl.travel.TravelAgencyCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TravelAgencyLikeCustomRepositoryImpl extends QuerydslRepositorySupport implements TravelAgencyLikeCustomRepository {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public TravelAgencyLikeCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgencyLike.class);
        this.queryFactory = queryFactory;
    }

    QTravelAgencyLike travelAgencyLike = QTravelAgencyLike.travelAgencyLike;
    QAttachFile attachFile = QAttachFile.attachFile;


    @Override
    public PageImpl<TravelAgencyLikeOnlyDto> findByCustomTripUser_Id(Long id, Pageable pageable) {
        var travelAgencyLikePage =  queryFactory.select(Projections.bean(TravelAgencyLikeOnlyDto.class,
                travelAgencyLike.id.as("id"),
                travelAgencyLike.tripUser.as("tripUser"),
                        attachFile.filePath.as("filePath"),
                travelAgencyLike.travelAgencyList.as("travelAgencyList"),
                travelAgencyLike.deleted.as("deleted"),
                travelAgencyLike.createdAt.as("createdAt"),
                travelAgencyLike.createdBy.as("createdBy"),
                travelAgencyLike.modifiedAt.as("modifiedAt"),
                travelAgencyLike.modifiedBy.as("modifiedBy")))
                .from(travelAgencyLike)
                .leftJoin(attachFile)
                .on(travelAgencyLike.travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyLike.tripUser.id.eq(id))
                .orderBy(travelAgencyLike.id.asc())
                .fetch();
        return new PageImpl<>(travelAgencyLikePage,pageable,travelAgencyLikePage.size());


    }
}
