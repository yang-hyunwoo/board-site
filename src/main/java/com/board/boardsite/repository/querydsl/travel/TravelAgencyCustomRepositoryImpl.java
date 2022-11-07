package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TravelAgencyCustomRepositoryImpl extends QuerydslRepositorySupport implements TravelAgencyCustomRepository {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public TravelAgencyCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgency.class);
        this.queryFactory = queryFactory;
    }

    QTravelAgency travelAgency = QTravelAgency.travelAgency;
    QAttachFile attachFile = QAttachFile.attachFile;
    @Override
    public List<TravelAgencyOnlyListDto> findTravelAgencyRandomCount(int count) {
        return queryFactory.select(Projections.bean(TravelAgencyOnlyListDto.class,
                        travelAgency.id.as("id"),
                        travelAgency.name.as("name"),
                        travelAgency.address.as("address"),
                        travelAgency.tel.as("tel"),
                        travelAgency.detail.as("detail"),
                        travelAgency.fileId.as("fileId"),
                        attachFile.filePath.as("filePath"),
                        travelAgency.comment.as("comment"),
                        travelAgency.deleted.as("deleted"),
                travelAgency.createdAt.as("createdAt"),
                travelAgency.createdBy.as("createdBy"),
                travelAgency.modifiedAt.as("modifiedAt"),
                travelAgency.modifiedBy.as("modifiedBy")))
                .from(travelAgency)
                .leftJoin(attachFile)
                .on(travelAgency.fileId.eq(attachFile.fileId))
                .where(travelAgency.deleted.eq(false))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(NumberExpression.random().asc())
                .limit(count)
                .fetch();
    }

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomAllByDeleted(boolean deleted, Pageable pageable) {
        var travelAgencyList =  queryFactory.select(Projections.bean(TravelAgencyOnlyListDto.class,
                        travelAgency.id.as("id"),
                        travelAgency.name.as("name"),
                        travelAgency.address.as("address"),
                        travelAgency.tel.as("tel"),
                        travelAgency.detail.as("detail"),
                        travelAgency.fileId.as("fileId"),
                        attachFile.filePath.as("filePath"),
                        travelAgency.comment.as("comment"),
                        travelAgency.deleted.as("deleted"),
                        travelAgency.createdAt.as("createdAt"),
                        travelAgency.createdBy.as("createdBy"),
                        travelAgency.modifiedAt.as("modifiedAt"),
                        travelAgency.modifiedBy.as("modifiedBy")))
                .from(travelAgency)
                .leftJoin(attachFile)
                .on(travelAgency.fileId.eq(attachFile.fileId))
                .where(travelAgency.deleted.eq(deleted))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomByNameContainingAndDeleted(String travelAgencyName , boolean deleted, Pageable pageable) {
        var travelAgencyList =  queryFactory.select(Projections.bean(TravelAgencyOnlyListDto.class,
                        travelAgency.id.as("id"),
                        travelAgency.name.as("name"),
                        travelAgency.address.as("address"),
                        travelAgency.tel.as("tel"),
                        travelAgency.detail.as("detail"),
                        travelAgency.fileId.as("fileId"),
                        attachFile.filePath.as("filePath"),
                        travelAgency.comment.as("comment"),
                        travelAgency.deleted.as("deleted"),
                        travelAgency.createdAt.as("createdAt"),
                        travelAgency.createdBy.as("createdBy"),
                        travelAgency.modifiedAt.as("modifiedAt"),
                        travelAgency.modifiedBy.as("modifiedBy")))
                .from(travelAgency)
                .leftJoin(attachFile)
                .on(travelAgency.fileId.eq(attachFile.fileId))
                .where(travelAgency.deleted.eq(deleted),
                        travelAgency.name.contains(travelAgencyName))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

}
