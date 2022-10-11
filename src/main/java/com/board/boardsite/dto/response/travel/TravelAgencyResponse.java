package com.board.boardsite.dto.response.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.travel.TravelAgencyDto;

public record TravelAgencyResponse(
        Long id,
        String name,
        String tel,
        String detail,
        String comment,
        Long fileId,
        String address,
        boolean deleted
) {
    public static TravelAgencyResponse of(Long id,
                                String name,
                                String tel,
                                String detail,
                                String comment,
                                Long fileId,
                                String address,
                                boolean deleted) {
        return new TravelAgencyResponse(
                id,
                name,
                tel,
                detail,
                comment,
                fileId,
                address,
                deleted);
    }

    public static TravelAgencyResponse from(TravelAgencyDto dto){
        return new TravelAgencyResponse(
                dto.id(),
                dto.name(),
                dto.tel(),
                dto.detail(),
                dto.comment(),
                dto.fileId(),
                dto.address(),
                dto.deleted()
        );
    }

}
