package com.board.boardsite.dto.request.travel;

import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.user.TripUserDto;

public record TravelAgencyReservationRequest(
        Long travelAgencyId,
        Long travelAgencyListId,
        String merchantUid,
        String impUid,
        String payEmail,
        String payName,
        int paid,
        int personCount
) {
    public static TravelAgencyReservationRequest of(Long travelAgencyId,
                                          Long travelAgencyListId,
                                          String merchantUid,
                                          String impUid,
                                          String payEmail,
                                          String payName,
                                          int paid,
                                          int personCount) {
        return new TravelAgencyReservationRequest(travelAgencyId,
                travelAgencyListId,
                merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                personCount);
    }

    public TravelAgencyReservationDto toDto(TripUserDto tripUserDto) {
        return TravelAgencyReservationDto.of(
                travelAgencyId,
                travelAgencyListId,
                tripUserDto,
                merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                personCount
        );
    }
}
