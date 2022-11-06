package com.board.boardsite.dto.response.travel;

import com.board.boardsite.dto.travel.TravelAgencyListOnlyListDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.travel.TravelAgencyReservationOnlyListDto;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

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
        LocalDateTime createdAt,
        String filePath,
        String thumbPath
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
                                                     LocalDateTime createdAt,
                                                     String filePath,
                                                     String thumbPath)
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
                createdAt,
                filePath,
                thumbPath);
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
                dto.createdAt(),
                null,
                null
        );
    }
    public static TravelAgencyReservationResponse from(TravelAgencyReservationOnlyListDto dto){
        return new TravelAgencyReservationResponse(
                dto.getId(),
                dto.getImpUid(),
                dto.getMerchantUid(),
                dto.getPaid(),
                dto.getPersonCount(),
                dto.isDeleted(),
                dto.getTravelAgencyDto().getName(),
                dto.getTravelAgencyListDto().getTitle(),
                dto.getTravelAgencyDto().getId(),
                dto.getTravelAgencyListDto().getId(),
                dto.getTravelAgencyListDto().getThumnbnailFileId(),
                dto.getQrCodeId(),
                dto.getCreatedAt(),
                dto.getFilePath(),
                dto.getThumbPath()
        );

    }
}
