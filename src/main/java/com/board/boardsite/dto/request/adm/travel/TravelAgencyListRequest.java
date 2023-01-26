package com.board.boardsite.dto.request.adm.travel;

import com.board.boardsite.domain.common.AttachFile;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import io.swagger.annotations.ApiModelProperty;


public record TravelAgencyListRequest(

        @ApiModelProperty(value ="여행사id" , example = "1" , required = true)
        Long travel_agency_id ,

        @ApiModelProperty(value ="도시" , example = "부산" , required = true)
        String city ,

        @ApiModelProperty(value ="내용" , example = "as" , required = true)
        String content ,
        @ApiModelProperty(value ="최대 인원" , example = "1" , required = false)
        int person_max_count ,

        @ApiModelProperty(value ="실제 가격" , example = "1" , required = false)
        int real_paid ,

        @ApiModelProperty(value ="할인 가격" , example = "1" , required = false)
        int sale_paid ,

        @ApiModelProperty(value ="할인률" , example = "1" , required = false)
        int sale_percent ,

        @ApiModelProperty(value ="썸네일id" , example = "1" , required = false)

        Long thumnbnailFileId ,

        @ApiModelProperty(value ="제목" , example = "1" , required = false)
        String title ,
        @ApiModelProperty(value ="여행 종료일" , example = "20221111" , required = false)
        String travel_end_at ,

        @ApiModelProperty(value ="실제 여행일" , example = "20221111" , required = false)
        String travelRealTripAt
) {
    public static TravelAgencyListRequest of(Long travel_agency_id,
                                   String city,
                                   String content,
                                   int person_max_count,
                                   int real_paid,
                                   int sale_paid,
                                   int sale_percent,
                                   Long thumnbnailFileId,
                                   String title,
                                   String travel_end_at,
                                   String travelRealTripAt) {
        return new TravelAgencyListRequest(
                travel_agency_id,
                city,
                content,
                person_max_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                title,
                travel_end_at,
                travelRealTripAt);
    }

    public TravelAgencyListDto toDto() {
        return TravelAgencyListDto.of(travel_agency_id,
                city,
                content,
                person_max_count,
                real_paid,
                sale_paid,
                sale_percent,
                thumnbnailFileId,
                title,
                travel_end_at,
                travelRealTripAt);
    }
}
