package com.board.boardsite.dto.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TravelAgencyReservationOnlyListDto{
        Long id;
        Long travelAgencyId;
        Long travelAgencyListId;
        TripUser tripUser;
        String merchantUid;
        String impUid;
        String payEmail;
        String payName;
        int paid;
        int realPaid;
        int personCount;
        int salePercent;
        boolean deleted;
        Long qrCodeId;
        boolean qrChk;
        TravelAgency travelAgencyDto;
        TravelAgencyList travelAgencyListDto;
        LocalDateTime createdAt;
        String filePath;
        String thumbPath;

    public TravelAgencyReservationOnlyListDto(Long id,
                                              Long travelAgencyId,
                                              Long travelAgencyListId,
                                              TripUser tripUser,
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
                                              boolean qrChk,
                                              TravelAgency travelAgencyDto,
                                              TravelAgencyList travelAgencyListDto,
                                              LocalDateTime createdAt,
                                              String filePath,
                                              String thumbPath) {
        this.id = id;
        this.travelAgencyId = travelAgencyId;
        this.travelAgencyListId = travelAgencyListId;
        this.tripUser = tripUser;
        this.merchantUid = merchantUid;
        this.impUid = impUid;
        this.payEmail = payEmail;
        this.payName = payName;
        this.paid = paid;
        this.realPaid = realPaid;
        this.personCount = personCount;
        this.salePercent = salePercent;
        this.deleted = deleted;
        this.qrCodeId = qrCodeId;
        this.qrChk = qrChk;
        this.travelAgencyDto = travelAgencyDto;
        this.travelAgencyListDto = travelAgencyListDto;
        this.createdAt = createdAt;
        this.filePath = filePath;
        this.thumbPath = thumbPath;
    }
}