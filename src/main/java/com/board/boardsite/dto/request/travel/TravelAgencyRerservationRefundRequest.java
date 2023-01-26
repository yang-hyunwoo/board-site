package com.board.boardsite.dto.request.travel;

import io.swagger.annotations.ApiModelProperty;

public record TravelAgencyRerservationRefundRequest(

        @ApiModelProperty(value ="아임포트uid" , example = "1" , required = true)
        String impUid,

        @ApiModelProperty(value ="가격" , example = "1" , required = true)
        int money,

        @ApiModelProperty(value ="구매 수량" , example = "1" , required = true)
        int personCount,

        @ApiModelProperty(value ="id" , example = "1" , required = true)
        Long id,

        @ApiModelProperty(value ="여행 목록 id" , example = "1" , required = true)
        Long travelAgencyListId
) {
    public static TravelAgencyRerservationRefundRequest of(String impUid,
                                                           int money,
                                                           int personCount,
                                                           Long id,
                                                           Long travelAgencyListId)
    {
        return new TravelAgencyRerservationRefundRequest(impUid,
                money,
                personCount,
                id,
                travelAgencyListId);
    }

}
