package com.board.boardsite.dto.response.travel;

import com.board.boardsite.dto.travel.TravelAgencyLikeDto;
import com.board.boardsite.dto.travel.TravelAgencyLikeOnlyDto;

import java.time.LocalDateTime;

public record TravelAgencyLikeResponse(
        Long id,
        Long talId,
        String talTitle,
        String taName,
        String talCity,
        Long talThumbnail,
        String filePath,
        int talReadCount,
        String talTravelStartAt,
        String talTravelEndAt,
        int talRealPaid,
        int talSalePaid,
        int talSalePercent,
        boolean deleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
    public static TravelAgencyLikeResponse of(Long id,
                                              Long talId,
                                              String talTitle,
                                              String taName,
                                              String talCity,
                                              Long talThumbnail,
                                              String filePath,
                                              int talReadCount,
                                              String talTravelStartAt,
                                              String talTravelEndAt,
                                              int talRealPaid,
                                              int talSalePaid,
                                              int talSalePercent,
                                              boolean deleted,
                                              LocalDateTime createdAt,
                                              String createdBy,
                                              LocalDateTime modifiedAt,
                                              String modifiedBy) {
        return new TravelAgencyLikeResponse(id,
                talId,
                talTitle,
                taName,
                talCity,
                talThumbnail,
                null,
                talReadCount,
                talTravelStartAt,
                talTravelEndAt,
                talRealPaid,
                talSalePaid,
                talSalePercent,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static TravelAgencyLikeResponse from(TravelAgencyLikeDto dto) {
        return new TravelAgencyLikeResponse(
                dto.id(),
                dto.travelAgencyList().id(),
                dto.travelAgencyList().title(),
                dto.travelAgencyList().travelAgency().getName(),
                dto.travelAgencyList().city(),
                dto.travelAgencyList().thumnbnailFileId(),
                null,
                dto.travelAgencyList().read_count(),
                dto.travelAgencyList().travel_start_at(),
                dto.travelAgencyList().travel_end_at(),
                dto.travelAgencyList().real_paid(),
                dto.travelAgencyList().sale_paid(),
                dto.travelAgencyList().sale_percent(),
                dto.deleted(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

    public static TravelAgencyLikeResponse from(TravelAgencyLikeOnlyDto dto) {
        return new TravelAgencyLikeResponse(
                dto.getId(),
                dto.getTravelAgencyList().getId(),
                dto.getTravelAgencyList().getTitle(),
                dto.getTravelAgencyList().getTravelAgency().getName(),
                dto.getTravelAgencyList().getCity(),
                dto.getTravelAgencyList().getThumnbnailFileId(),
                dto.getFilePath(),
                dto.getTravelAgencyList().getReadCount(),
                dto.getTravelAgencyList().getTravelStartAt(),
                dto.getTravelAgencyList().getTravelEndAt(),
                dto.getTravelAgencyList().getRealPaid(),
                dto.getTravelAgencyList().getSalePaid(),
                dto.getTravelAgencyList().getSalePercent(),
                dto.isDeleted(),
                dto.getCreatedAt(),
                dto.getCreatedBy(),
                dto.getModifiedAt(),
                dto.getModifiedBy()
        );
    }
}
