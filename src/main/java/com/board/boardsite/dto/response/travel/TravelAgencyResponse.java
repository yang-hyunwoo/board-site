package com.board.boardsite.dto.response.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.travel.TravelAgencyDto;

public record TravelAgencyResponse(
        Long id,
        String name,
        String tel,
        String detail,
        String address
) {
    public static TravelAgencyResponse of(Long id,
                                String name,
                                String tel,
                                String detail,
                                String address) {
        return new TravelAgencyResponse(
                id,
                name,
                tel,
                detail,
                address);
    }

    public static TravelAgencyResponse from(TravelAgencyDto dto){
        return new TravelAgencyResponse(
                dto.id(),
                dto.name(),
                dto.tel(),
                dto.detail(),
                dto.address()
        );
    }

}
