package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;

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
        boolean deleted
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
                                      boolean deleted) {
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
                deleted
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
                                                int personCount)
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
                true
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
                entity.isDeleted()
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
                tripUser,
                travelAgency,
                travelAgencyList
        );
    }
}
