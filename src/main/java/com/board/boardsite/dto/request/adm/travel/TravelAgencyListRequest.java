package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyListDto;


public record TravelAgencyListRequest(
        Long travel_agency_id ,
        String city ,
        String content ,
        int person_max_count ,
        int real_paid ,
        int sale_paid ,
        int sale_percent ,
        Long thumnbnailFileId ,
        String title ,
        String travel_end_at ,
        String travelRealTripAt
) {
    public static TravelAgencyListRequest of(Long travel_agency_id,
                                   String city,
                                   String content,
                                   int person_max_count,
                                   int real_paid,
                                   int sale_paid,
                                   int sale_percent,
                                   Long thumnbnailFileId,
                                   String title,
                                   String travel_end_at,
                                   String travelRealTripAt) {
        return new TravelAgencyListRequest(
                travel_agency_id,
                city,
                content,
                person_max_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                title,
                travel_end_at,
                travelRealTripAt);
    }

    public TravelAgencyListDto toDto() {
        return TravelAgencyListDto.of(travel_agency_id,
                city,
                content,
                person_max_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                title,
                travel_end_at,
                travelRealTripAt);
    }
}
