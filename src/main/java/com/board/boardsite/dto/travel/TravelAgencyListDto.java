package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgencyList;

import java.time.LocalDateTime;
import java.util.Set;

public record TravelAgencyListDto(
        Long id,
        Long travel_agency_id,
        String city,
        String content,
        int person_count,
        int person_max_count,
        int read_count,
        int real_paid,
        int sale_paid,
        int sale_percent,
        Long thumnbnailFileId,
        Integer sort,
        String title,
        String travel_start_at,
        String travel_end_at,
        String travelRealTripAt,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        TravelAgency travelAgency,
        int like_count,
        boolean deleted,
        Set<TravelAgencyLike> travelAgencyLike
) {

    public static TravelAgencyListDto of(Long id,
                               Long travel_agency_id,
                               String city,
                               String content,
                               int person_count,
                               int person_max_count,
                               int read_count,
                               int real_paid,
                               int sale_paid,
                               int sale_percent,
                               Long thumnbnailFileId,
                               Integer sort,
                               String title,
                               String travel_start_at,
                               String travel_end_at,
                               String travelRealTripAt,
                               LocalDateTime createdAt,
                               String createdBy,
                               LocalDateTime modifiedAt,
                               String modifiedBy,
                               TravelAgency travelAgency,
                               int like_count,
                               boolean deleted,
                               Set<TravelAgencyLike> travelAgencyLike) {
        return new TravelAgencyListDto(
                id,
                travel_agency_id,
                city,
                content,
                person_count,
                person_max_count,
                read_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                sort,
                title,
                travel_start_at,
                travel_end_at,
                travelRealTripAt,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy,
                travelAgency,
                like_count,
                deleted,
                travelAgencyLike);
    }

    public static TravelAgencyListDto of(
                                         Long travel_agency_id,
                                         String city,
                                         String content,
                                         int person_count,
                                         int person_max_count,
                                         int read_count,
                                         int real_paid,
                                         int sale_paid,
                                         int sale_percent,
                                         Long thumnbnailFileId,
                                         String title,
                                         String travel_start_at,
                                         String travel_end_at,
                                         String travelRealTripAt,
                                         TravelAgency travelAgency,
                                         int like_count,
                                         Set<TravelAgencyLike> travelAgencyLike)
    {
        return new TravelAgencyListDto(
                null,
                travel_agency_id,
                city,
                content,
                person_count,
                person_max_count,
                read_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                null,
                title,
                travel_start_at,
                travel_end_at,
                travelRealTripAt,
                null,
                null,
                null,
                null,
                travelAgency,
                like_count,
                false,
                travelAgencyLike);
    }

    public static TravelAgencyListDto from(TravelAgencyList entity) {
        return new TravelAgencyListDto(
                entity.getId(),
                entity.getTravelAgency().getId(),
                entity.getCity(),
                entity.getContent(),
                entity.getPersonCount(),
                entity.getPersonMaxCount(),
                entity.getReadCount(),
                entity.getRealPaid(),
                entity.getSalePaid(),
                entity.getSalePercent(),
                entity.getThumnbnailFileId(),
                entity.getSort(),
                entity.getTitle(),
                entity.getTravelStartAt(),
                entity.getTravelEndAt(),
                entity.getTravelRealTripAt(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getTravelAgency(),
                entity.getTravelAgencyLikes().size(),
                entity.isDeleted(),
                entity.getTravelAgencyLikes()
        );
    }

    public TravelAgencyList toEntity(TravelAgency travelAgency){
        return TravelAgencyList.of(
                city,
                travel_start_at,
                travel_end_at,
                travelRealTripAt,
                title,
                content,
                real_paid,
                sale_percent,
                sale_paid,
                person_count,
                person_max_count,
                read_count,
                thumnbnailFileId,
                sort,
                travelAgency
        );
    }

}
