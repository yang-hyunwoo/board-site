package com.board.boardsite.dto.request.adm.tour;

import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record TourRequest(

        @ApiModelProperty(value ="제목" , example = "test" , required = true)
        String title,

        @ApiModelProperty(value ="내용" , example = "test" , required = true)
        String content,

        @ApiModelProperty(value ="썸네일파일id" , example = "1" , required = false)
        Long thumbnailId,

        @ApiModelProperty(value ="도시" , example = "부산" , required = false)
        String city
) {
    public static TourRequest of(String title,
                                 String content,
                                 Long thumbnailId,
                                 String city) {
        return new TourRequest(title,
                content,
                thumbnailId,
                city);
    }

    public TourDto toDto(TripUserDto tripUserDto) {
        return TourDto.of(
                tripUserDto,
                title,
                content,
                thumbnailId,
                city
        );
    }
}

