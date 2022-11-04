package com.board.boardsite.repository.querydsl.travel.adm.travel;

import com.board.boardsite.domain.common.QAttachFile;
import com.board.boardsite.domain.travel.QTravelAgency;
import com.board.boardsite.domain.travel.QTravelAgencyList;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdmTravelAgencyListCustomRepositoryImpl extends QuerydslRepositorySupport implements AdmTravelAgencyListCustomRepository {


    private final JPAQueryFactory queryFactory;

    public AdmTravelAgencyListCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(TravelAgencyList.class);
        this.queryFactory = queryFactory;
    }

    QTravelAgencyList travelAgencyList = QTravelAgencyList.travelAgencyList;
    QAttachFile attachFile = QAttachFile.attachFile;

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByCreatedAtBetweenOrderById(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());

    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTitleContainingAndCreatedAtBetweenOrderById(String input, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate),
                        travelAgencyList.title.eq(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findByCustomTravelAgencyNameContainingAndCreatedAtBetweenOrderById(String input, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.name.eq(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelRealTripAtBetweenOrderById(String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTitleContainingAndTravelRealTripAtBetweenOrderById(String input, String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate),
                        travelAgencyList.title.contains(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(String input, String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.name.contains(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndCreatedAtBetweenOrderById(Long travelAgencyId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTitleContainingAndCreatedAtBetweenOrderById(Long travelAgencyId, String input, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId),
                        travelAgencyList.title.contains(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelAgencyNameContainingAndCreatedAtBetweenOrderById(Long travelAgencyId, String input, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.createdAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId),
                        travelAgencyList.travelAgency.name.contains(input))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelRealTripAtBetweenOrderById(Long travelAgencyId, String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTitleContainingAndTravelRealTripAtBetweenOrderById(Long travelAgencyId, String input, String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate),
                        travelAgencyList.title.contains(input),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }

    @Override
    public PageImpl<TravelAgencyListOnlyListDto> findCustomByTravelAgency_IdAndTravelAgencyNameContainingAndTravelRealTripAtBetweenOrderById(Long travelAgencyId, String input, String startDate, String endDate, Pageable pageable) {
        var travelAgencyListPage = queryFactory.select(Projections.bean(TravelAgencyListOnlyListDto.class,
                        travelAgencyList.id.as("id"),
                        travelAgencyList.travelAgency.id.as("travel_agency_id"),
                        travelAgencyList.travelAgency.name.as("travel_name"),
                        travelAgencyList.city.as("city"),
                        travelAgencyList.readCount.as("read_count"),
                        travelAgencyList.content.as("content"),
                        travelAgencyList.realPaid.as("real_paid"),
                        travelAgencyList.salePaid.as("sale_paid"),
                        travelAgencyList.salePercent.as("sale_percent"),
                        attachFile.filePath.as("filePath"),
                        travelAgencyList.sort.as("sort"),
                        travelAgencyList.travelRealTripAt.as("travelRealTripAt"),
                        travelAgencyList.deleted.as("deleted"),
                        travelAgencyList.travelStartAt.as("travel_start_at"),
                        travelAgencyList.travelEndAt.as("travel_end_at"),
                        travelAgencyList.title.as("title")))
                .from(travelAgencyList)
                .leftJoin(attachFile)
                .on(travelAgencyList.thumnbnailFileId.eq(attachFile.fileId))
                .where(travelAgencyList.travelRealTripAt.between(startDate,endDate),
                        travelAgencyList.travelAgency.name.contains(input),
                        travelAgencyList.travelAgency.id.eq(travelAgencyId))
                .orderBy(travelAgencyList.sort.asc())
                .fetch();
        return new PageImpl<>(travelAgencyListPage, pageable, travelAgencyListPage.size());
    }
}
