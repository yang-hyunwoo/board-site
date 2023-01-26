package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import io.swagger.annotations.ApiModelProperty;

public record QrcodeUserUpdateRequest(

        @ApiModelProperty(value ="아이디" , example = "1" , required = true)
        Long id,

        @ApiModelProperty(value ="사용자id" , example = "1" , required = true)
        Long userId,

        @ApiModelProperty(value ="갯수" , example = "1" , required = true)
        int count,

        @ApiModelProperty(value ="여행사 여행 id" , example = "1" , required = true)
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
