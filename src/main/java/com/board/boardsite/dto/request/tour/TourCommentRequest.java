package com.board.boardsite.dto.request.tour;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.tour.TourCommentDto;
import com.board.boardsite.dto.user.TripUserDto;

public record TourCommentRequest(
        Long tourId,
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
