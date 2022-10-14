package com.board.boardsite.dto.response.adm.travel;

import com.board.boardsite.dto.adm.travel.AdmTravelAgencyReservationDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.user.AdmTripUserDto;
import com.board.boardsite.dto.user.TripUserDto;

import java.time.LocalDateTime;

public record AdmTravelAgencyReservationResponse(
        Long id,
        String imp_uid,
        String merchant_uid,
        int paid,
        int personCount,
        boolean deleted,
        String travelAgencyName,
        String travelAgencyListTitle,
        Long travelAgencyId,
        Long travelAgencyListId,
        Long thumbFileId,
        Long qrCodeId,
        LocalDateTime createdAt,
        AdmTripUserDto tripUserDto
) {
    public static AdmTravelAgencyReservationResponse of(Long id,
                                                        String imp_uid,
                                                        String merchant_uid,
                                                        int paid,
                                                        int personCount,
                                                        boolean deleted,
                                                        String travelAgencyName,
                                                        String travelAgencyListTitle,
                                                        Long travelAgencyId,
                                                        Long travelAgencyListId,
                                                        Long thumbFileId,
                                                        Long qrCodeId,
                                                        LocalDateTime createdAt,
                                                        AdmTripUserDto tripUserDto)
    {
        return new AdmTravelAgencyReservationResponse(id,
                imp_uid,
                merchant_uid,
                paid,
                personCount,
                deleted,
                travelAgencyName,
                travelAgencyListTitle,
                travelAgencyId,
                travelAgencyListId,
                thumbFileId,
                qrCodeId,
                createdAt,
                tripUserDto);
    }

    public static AdmTravelAgencyReservationResponse from(AdmTravelAgencyReservationDto dto) {
        return new AdmTravelAgencyReservationResponse(
                dto.id(),
                dto.impUid(),
                dto.merchantUid(),
                dto.paid(),
                dto.personCount(),
                dto.deleted(),
                dto.travelAgencyDto().name(),
                dto.travelAgencyListDto().title(),
                dto.travelAgencyDto().id(),
                dto.travelAgencyListDto().id(),
                dto.travelAgencyListDto().thumnbnailFileId(),
                dto.qrCodeId(),
                dto.createdAt(),
                dto.admTripUser()
        );
    }

}
