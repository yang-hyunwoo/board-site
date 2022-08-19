package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;

import java.time.LocalDateTime;

public record TravelAgencyListDto(
        Long id,
        Long travel_agency_id,
        String city,
        String content,
        int like_count,
        int person_count,
        int person_max_count,
        int read_count,
        int real_paid,
        int sale_paid,
        int sale_percent,
        String title,
        String travel_start_at,
        String travel_end_at,

        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static TravelAgencyListDto of(Long id,
                               Long travel_agency_id,
                               String city,
                               String content,
                               int like_count,
                               int person_count,
                               int person_max_count,
                               int read_count,
                               int real_paid,
                               int sale_paid,
                               int sale_percent,
                               String title,
                               String travel_start_at,
                               String travel_end_at,
                               LocalDateTime createdAt,
                               String createdBy,
                               LocalDateTime modifiedAt,
                               String modifiedBy) {
        return new TravelAgencyListDto(
                id,
                travel_agency_id,
                city,
                content,
                like_count,
                person_count,
                person_max_count,
                read_count,
                real_paid,
                sale_paid,
                sale_percent,
                title,
                travel_start_at,
                travel_end_at,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static TravelAgencyListDto of(
                                         Long travel_agency_id,
                                         String city,
                                         String content,
                                         int like_count,
                                         int person_count,
                                         int person_max_count,
                                         int read_count,
                                         int real_paid,
                                         int sale_paid,
                                         int sale_percent,
                                         String title,
                                         String travel_start_at,
                                         String travel_end_at) {
        return new TravelAgencyListDto(
                null,
                travel_agency_id,
                city,
                content,
                like_count,
                person_count,
                person_max_count,
                read_count,
                real_paid,
                sale_paid,
                sale_percent,
                title,
                travel_start_at,
                travel_end_at,
                null,
                null,
                null,
                null);
    }

    public static TravelAgencyListDto from(TravelAgencyList entity) {
        return new TravelAgencyListDto(
                entity.getId(),
                entity.getTravelAgency().getId(),
                entity.getCity(),
                entity.getContent(),
                entity.getLikeCount(),
                entity.getPersonCount(),
                entity.getPersonMaxCount(),
                entity.getReadCount(),
                entity.getRealPaid(),
                entity.getSalePaid(),
                entity.getSalePercent(),
                entity.getTitle(),
                entity.getTravelStartAt(),
                entity.getTravelEndAt(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public TravelAgencyList toEntity(TravelAgency travelAgency){
        return TravelAgencyList.of(
                city,
                travel_start_at,
                travel_end_at,
                title,
                content,
                real_paid,
                sale_percent,
                sale_paid,
                person_count,
                person_max_count,
                read_count,
                like_count,
                travelAgency
        );
    }

}
