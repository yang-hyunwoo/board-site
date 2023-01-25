package com.board.boardsite.dto.request.tour;

import com.board.boardsite.dto.tour.TourCommentDto;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record TourCommentRequest(

        @ApiModelProperty(value ="여행Id" , example = "1" , required = true)
        Long tourId,

        @ApiModelProperty(value ="content" , example = "내용" , required = true)
        String content
)
{
    public static TourCommentRequest of(Long tourId, String content) {
        return new TourCommentRequest(tourId,content);
    }

    public TourCommentDto toDto(TripUserDto tripUserDto){
        return TourCommentDto.of(
                tourId,
                tripUserDto,
                content
        );
    }
}
