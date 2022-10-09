package com.board.boardsite.dto.request.adm.tour;

import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.dto.tour.TourDto;
import com.board.boardsite.dto.user.TripUserDto;

public record TourRequest(
        String title,
        String content,
        Long thumbnailId,
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
