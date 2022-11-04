package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class AdmTravelAgencyCustomRepositoryImpl extends QuerydslRepositorySupport implements AdmTravelAgencyCustomRepository {


    private final JPAQueryFactory queryFactory;

    public AdmTravelAgencyCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgency.class);
        this.queryFactory = queryFactory;
    }


    QTravelAgency travelAgency = QTravelAgency.travelAgency;
    QAttachFile attachFile = QAttachFile.attachFile;

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomAll(Pageable pageable) {
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
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomByNameContaining(String travelAgencyName ,Pageable pageable) {
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
                .where(travelAgency.name.contains(travelAgencyName))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomAllDivide(Long travelId, Pageable pageable) {
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
                .where(travelAgency.id.eq(travelId))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

    @Override
    public PageImpl<TravelAgencyOnlyListDto> findCustomByNameContainingDivide(Long travelId, String travelAgencyName, Pageable pageable) {
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
                .where(travelAgency.id.eq(travelId),
                        travelAgency.name.contains(travelAgencyName))
                .groupBy(travelAgency.id,attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetch();

        return new PageImpl<>(travelAgencyList, pageable, travelAgencyList.size());
    }

    @Override
    public Optional<TravelAgencyOnlyListDto> findCustomDetail(Long travelAgencyId) {
        return Optional.ofNullable(queryFactory.select(Projections.bean(TravelAgencyOnlyListDto.class,
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
                .where(travelAgency.id.eq(travelAgencyId))
                .groupBy(travelAgency.id, attachFile.filePath)
                .orderBy(travelAgency.id.asc())
                .fetchOne());
    }
}
