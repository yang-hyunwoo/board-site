package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.dto.travel.TravelAgencyDto;
import io.swagger.annotations.ApiModelProperty;

public record TravelAgencyRequest(

        @ApiModelProperty(value ="이름" , example = "test" , required = true)
        String name ,

        @ApiModelProperty(value ="주소" , example = "동작구" , required = true)
        String address ,

        @ApiModelProperty(value ="전화번호" , example = "111-111-111" , required = true)
        String tel ,

        @ApiModelProperty(value ="상세 내용" , example = "12345" , required = true)
        String detail ,

        @ApiModelProperty(value ="파일 id" , example = "1" , required = false)
        Long file_id ,

        @ApiModelProperty(value ="내용" , example = "asdf" , required = true)
        String comment
) {

    public static TravelAgencyRequest of(String name,
                                         String address,
                                         String tel,
                                         String detail,
                                         Long file_id,
                                         String comment
    ) {
        return new TravelAgencyRequest(
                name,
                address,
                tel,
                detail,
                file_id,
        comment);
    }

    public TravelAgencyDto toDto() {
        return TravelAgencyDto.of(name,
                address,
                tel,
                detail,
                file_id,
                comment
        );
    }
}
