package com.board.boardsite.dto.response.travel;

import com.board.boardsite.dto.travel.TravelAgencyWithTravelAgencyListDto;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record TravelAgencyWithTravelAgencyListResponse(
        Long id,
        String title,
        String content,
        String tel,
        String detail,
        List<TravelAgencyListResponse> travelAgencyListResponse
) {
    public static TravelAgencyWithTravelAgencyListResponse of(Long id,
                                                    String title,
                                                    String content,
                                                    String tel,
                                                    String detail,
                                                              List<TravelAgencyListResponse> travelAgencyListResponse) {
        return new TravelAgencyWithTravelAgencyListResponse(
                id,
                title,
                content,
                tel,
                detail,
                travelAgencyListResponse);
    }

    public static TravelAgencyWithTravelAgencyListResponse from(TravelAgencyWithTravelAgencyListDto dto){
        return new TravelAgencyWithTravelAgencyListResponse(
                dto.id(),
                dto.name(),
                dto.name(),
                dto.tel(),
                dto.detail(),
                dto.travelAgencyListDtos().stream()
                        .map(TravelAgencyListResponse::from)
                        .toList()
        );
    }
}
