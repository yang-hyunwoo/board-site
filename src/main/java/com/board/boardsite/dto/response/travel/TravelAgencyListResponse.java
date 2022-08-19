package com.board.boardsite.dto.response.travel;

import com.board.boardsite.dto.travel.TravelAgencyListDto;

public record TravelAgencyListResponse(
        Long id,
        Long travel_agency_id,
        String travelAgencyName,
        String city,
        String title,
        String content,
        int sale_percent,
        int real_paid,
        int sale_paid,
        String travel_start_at,
        String travel_end_at,
        int like_count,
        int read_count,
        int person_count,
        int person_max_count
) {

    public static TravelAgencyListResponse of(Long id,
                                    Long travel_agency_id,
                                    String travelAgencyName,
                                    String city,
                                    String title,
                                    String content,
                                    int sale_percent,
                                    int real_paid,
                                    int sale_paid,
                                    String travel_start_at,
                                    String travel_end_at,
                                    int like_count,
                                    int read_count,
                                    int person_count,
                                    int person_max_count) {
        return new TravelAgencyListResponse(
                id,
                travel_agency_id,
                travelAgencyName,
                city,
                title,
                content,
                sale_percent,
                real_paid,
                sale_paid,
                travel_start_at,
                travel_end_at,
                like_count,
                read_count,
                person_count,
                person_max_count);
    }

    public static TravelAgencyListResponse from(TravelAgencyListDto dto){
        return new TravelAgencyListResponse(
                dto.id(),
                dto.travelAgency().getId(),
                dto.travelAgency().getName(),
                dto.city(),
                dto.title(),
                dto.content(),
                dto.sale_percent(),
                dto.real_paid(),
                dto.sale_paid(),
                dto.travel_start_at(),
                dto.travel_end_at(),
                dto.like_count(),
                dto.read_count(),
                dto.person_count(),
                dto.person_max_count()
        );

    }

}
