package com.board.boardsite.dto.request.travel;

import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record TravelAgencyReservationRequest(

        @ApiModelProperty(value ="여행사 id" , example = "1" , required = true)
        Long travelAgencyId,

        @ApiModelProperty(value ="여행사 여행 id" , example = "1" , required = true)
        Long travelAgencyListId,

        @ApiModelProperty(value ="아임포트meruid" , example = "1" , required = true)

        String merchantUid,

        @ApiModelProperty(value ="아임포트uid" , example = "1" , required = true)
        String impUid,

        @ApiModelProperty(value ="결제시이메일" , example = "test@naver.com" , required = true)
        String payEmail,

        @ApiModelProperty(value ="결제시이름" , example = "test" , required = true)
        String payName,

        @ApiModelProperty(value ="가격" , example = "1" , required = false)
        int paid,

        @ApiModelProperty(value ="구매수량" , example = "1" , required = false)
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
                personCount,
                null,
                null,
                null
        );
    }
}
