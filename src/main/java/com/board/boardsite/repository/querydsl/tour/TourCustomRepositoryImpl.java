package com.board.boardsite.repository.querydsl.tour;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.tour.QTour;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.dto.tour.TourOnlyListDto;
import com.board.boardsite.repository.querydsl.travel.template.MySQLJPATemplates;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    QTour tour = QTour.tour;
    QAttachFile attachFile = QAttachFile.attachFile;

    @Override
    public List<TourOnlyListDto> findTourRandomCount(int count) {

         return queryFactory.select(Projections.bean(TourOnlyListDto.class,
                        tour.id.as("id"),
                        tour.title.as("title"),
                        tour.content.as("content"),
                        tour.deleted.as("deleted"),
                        tour.readCount.as("readCount"),
                        tour.thumbnailId.as("thumbnailId"),
                        attachFile.filePath.as("filePath"),
                        tour.city.as("city"),
                        tour.createdAt.as("createdAt"),
                        tour.createdBy.as("createdBy"),
                        tour.modifiedAt.as("modifiedAt"),
                        tour.modifiedBy.as("modifiedBy")))
                .from(tour)
                .leftJoin(attachFile)
                .on(tour.thumbnailId.eq(attachFile.fileId))
                .where(tour.deleted.eq(false))
                 .groupBy(tour.id,attachFile.filePath)
                 .orderBy(NumberExpression.random().asc())
                .fetch();
    }

    @Override
    public PageImpl<TourOnlyListDto> findCustomAllByDeleted(boolean deleted, Pageable pageable) {

        var aa =  queryFactory.select(Projections.bean(TourOnlyListDto.class,
                        tour.id.as("id"),
                        tour.tripUser,
                        tour.title.as("title"),
                        tour.content.as("content"),
                        tour.deleted.as("deleted"),
                        tour.readCount.as("readCount"),
                        tour.thumbnailId.as("thumbnailId"),
                        attachFile.filePath.as("filePath"),
                        tour.city.as("city"),
                        tour.createdAt.as("createdAt"),
                        tour.createdBy.as("createdBy"),
                        tour.modifiedAt.as("modifiedAt"),
                        tour.modifiedBy.as("modifiedBy")))
                .from(tour)
                .leftJoin(attachFile)
                .on(tour.thumbnailId.eq(attachFile.fileId))
                .where(tour.deleted.eq(deleted))
                .fetch();
        return new PageImpl<>(aa, pageable, aa.size());
    }

    @Override
    public PageImpl<TourOnlyListDto> findCustomByTitleContainingAndDeleted(String searchKeyWord, boolean deleted, Pageable pageable) {
        var tourList =  queryFactory.select(Projections.bean(TourOnlyListDto.class,
                        tour.id.as("id"),
                        tour.tripUser,
                        tour.title.as("title"),
                        tour.content.as("content"),
                        tour.deleted.as("deleted"),
                        tour.readCount.as("readCount"),
                        tour.thumbnailId.as("thumbnailId"),
                        attachFile.filePath.as("filePath"),
                        tour.city.as("city"),
                        tour.createdAt.as("createdAt"),
                        tour.createdBy.as("createdBy"),
                        tour.modifiedAt.as("modifiedAt"),
                        tour.modifiedBy.as("modifiedBy")))
                .from(tour)
                .leftJoin(attachFile)
                .on(tour.thumbnailId.eq(attachFile.fileId))
                .where(tour.title.contains(searchKeyWord),
                        tour.deleted.eq(deleted))
                .fetch();
        return new PageImpl<>(tourList, pageable, tourList.size());
    }

    @Override
    public PageImpl<TourOnlyListDto> findCustomByCityContainingAndDeleted(String searchKeyWord, boolean deleted, Pageable pageable) {
        var tourList =  queryFactory.select(Projections.bean(TourOnlyListDto.class,
                        tour.id.as("id"),
                        tour.tripUser,
                        tour.title.as("title"),
                        tour.content.as("content"),
                        tour.deleted.as("deleted"),
                        tour.readCount.as("readCount"),
                        tour.thumbnailId.as("thumbnailId"),
                        attachFile.filePath.as("filePath"),
                        tour.city.as("city"),
                        tour.createdAt.as("createdAt"),
                        tour.createdBy.as("createdBy"),
                        tour.modifiedAt.as("modifiedAt"),
                        tour.modifiedBy.as("modifiedBy")))
                .from(tour)
                .leftJoin(attachFile)
                .on(tour.thumbnailId.eq(attachFile.fileId))
                .where(tour.city.contains(searchKeyWord),
                        tour.deleted.eq(deleted))
                .fetch();
        return new PageImpl<>(tourList, pageable, tourList.size());
    }

}
