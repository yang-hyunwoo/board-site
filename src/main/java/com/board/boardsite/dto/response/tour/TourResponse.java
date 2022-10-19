package com.board.boardsite.dto.response.tour;

import com.board.boardsite.dto.tour.TourDto;
import java.time.LocalDateTime;

public record TourResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content ,
        int readCount,
        Long thumbnailId,
        String city,

        boolean deleted

) {

    public static TourResponse of(Long id,
                                  LocalDateTime createdAt,
                                  String title,
                                  String content,
                                  int readCount,
                                  Long thumbnailId,
                                  String city,
                                  boolean deleted) {
        return new TourResponse(id,
                createdAt,
                title,
                content,
                readCount,
                thumbnailId,
                city,
                deleted);
    }

    public static TourResponse from(TourDto dto){
        return new TourResponse(
                dto.id(),
                dto.createdAt(),
                dto.title(),
                dto.content(),
                dto.readCount(),
                dto.thumbnailId(),
                dto.city(),
                dto.deleted()
        );
    }
}
