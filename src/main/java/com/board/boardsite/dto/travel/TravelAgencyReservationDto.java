package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;
import java.time.LocalDateTime;

public record TravelAgencyReservationDto(
        Long id ,
        Long travelAgencyId,
        Long travelAgencyListId,
        TripUserDto tripUser,
        String merchantUid,
        String impUid,
        String payEmail,
        String payName,
        int paid,
        int realPaid,
        int personCount,
        int salePercent,
        boolean deleted,
        Long qrCodeId,
        TravelAgencyDto travelAgencyDto,
        TravelAgencyListDto travelAgencyListDto,
        LocalDateTime createdAt
) {

    public static TravelAgencyReservationDto of(Long id,
                                                Long travelAgencyId,
                                                Long travelAgencyListId,
                                                TripUserDto tripUser,
                                                String merchantUid,
                                                String impUid,
                                                String payEmail,
                                                String payName,
                                                int paid,
                                                int realPaid,
                                                int personCount,
                                                int salePercent,
                                                boolean deleted,
                                                Long qrCodeId,
                                                TravelAgencyDto travelAgencyDto,
                                                TravelAgencyListDto travelAgencyListDto,
                                                LocalDateTime createdAt) {
        return new TravelAgencyReservationDto(id,
                travelAgencyId,
                travelAgencyListId,
                tripUser,
                merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                realPaid,
                personCount,
                salePercent,
                deleted,
                qrCodeId,
                travelAgencyDto,
                travelAgencyListDto,
                createdAt
        );
    }

    public static TravelAgencyReservationDto of(Long travelAgencyId,
                                                Long travelAgencyListId,
                                                TripUserDto tripUser,
                                                String merchantUid,
                                                String impUid,
                                                String payEmail,
                                                String payName,
                                                int paid,
                                                int personCount,
                                                Long qrCodeId,
                                                TravelAgencyDto travelAgencyDto,
                                                TravelAgencyListDto travelAgencyListDto)
    {
        return new TravelAgencyReservationDto(null,
                travelAgencyId,
                travelAgencyListId,
                tripUser,
                merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                0,
                personCount,
                0,
                true,
                null,
                travelAgencyDto,
                travelAgencyListDto,
                null
        );
    }



    public static TravelAgencyReservationDto from(TravelAgencyReservation entity) {
        return new TravelAgencyReservationDto(
                entity.getId(),
                entity.getTravelAgency().getId(),
                entity.getTravelAgencyList().getId(),
                TripUserDto.from(entity.getTripUser()),
                entity.getMerchantUid(),
                entity.getImpUid(),
                entity.getPayEmail(),
                entity.getPayName(),
                entity.getPaid(),
                entity.getRealPaid(),
                entity.getPersonCount(),
                entity.getSalePercent(),
                entity.isDeleted(),
                entity.getQrCodeId(),
                TravelAgencyDto.from(entity.getTravelAgency()),
                TravelAgencyListDto.from(entity.getTravelAgencyList()),
                entity.getCreatedAt()
        );
    }

    public  TravelAgencyReservation toEntity(TravelAgency travelAgency, TravelAgencyList travelAgencyList , TripUser tripUser) {
        return TravelAgencyReservation.of(
                merchantUid,
                impUid,
                payEmail,
                payName,
                paid,
                travelAgencyList.getSalePaid() * personCount,
                personCount,
                travelAgencyList.getSalePercent(),
                deleted,
                qrCodeId,
                tripUser,
                travelAgency,
                travelAgencyList
        );
    }
}
