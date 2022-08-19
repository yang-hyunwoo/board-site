package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record TravelAgencyWithTravelAgencyListDto(
        Long id,
        String name,
        String detail,
        String address,
        String tel,
        List<TravelAgencyListDto> travelAgencyListDtos
) {

    public static TravelAgencyWithTravelAgencyListDto of(Long id,
                                               String name,
                                               String detail,
                                               String address,
                                               String tel,
                                                         List<TravelAgencyListDto> travelAgencyListDtos) {
        return new TravelAgencyWithTravelAgencyListDto(
                id,
                name,
                detail,
                address,
                tel,
                travelAgencyListDtos);
    }

    public static TravelAgencyWithTravelAgencyListDto from(TravelAgency entity){
        return new TravelAgencyWithTravelAgencyListDto(
                entity.getId(),
                entity.getName(),
                entity.getDetail(),
                entity.getAddress(),
                entity.getTel(),
                entity.getTravelAgencyLists().stream()
                        .map(TravelAgencyListDto::from)
                        .toList()

        );
    }

}
