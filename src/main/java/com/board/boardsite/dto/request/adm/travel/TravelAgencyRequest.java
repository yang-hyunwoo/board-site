package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.user.TripUserDto;

public record TravelAgencyRequest(
        String name ,
        String address ,
        String tel ,
        String detail ,
        Long file_id ,
        String comment
) {

    public static TravelAgencyRequest of(String name,
                                         String address,
                                         String tel,
                                         String detail,
                                         Long file_id,
                                         String comment
    ) {
        return new TravelAgencyRequest(
                name,
                address,
                tel,
                detail,
                file_id,
        comment);
    }

    public TravelAgencyDto toDto() {
        return TravelAgencyDto.of(name,
                address,
                tel,
                detail,
                file_id,
                comment
        );
    }
}
