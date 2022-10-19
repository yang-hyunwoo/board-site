package com.board.boardsite.dto.response.travel;

import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import java.time.LocalDateTime;

public record TravelAgencyReservationResponse(
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
        LocalDateTime createdAt
) {
    public static TravelAgencyReservationResponse of(Long id,
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
                                                     LocalDateTime createdAt)
    {
        return new TravelAgencyReservationResponse(id,
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
                createdAt);
    }

    public static TravelAgencyReservationResponse from(TravelAgencyReservationDto dto) {
        return new TravelAgencyReservationResponse(
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
                dto.createdAt()
        );
    }

}
