package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyReservationDto;

public record QrcodeUserUpdateRequest(
        Long id,
        Long userId,
        int count,
        Long travelAgencyListId
) {
    public static QrcodeUserUpdateRequest of(Long id,
                                   Long userId,
                                             int count,
                                             Long travelAgencyListId) {
        return new QrcodeUserUpdateRequest(id ,
                userId,
                count,
                travelAgencyListId);
    }

}
