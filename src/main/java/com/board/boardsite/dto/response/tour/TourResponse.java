package com.board.boardsite.dto.response.tour;

import com.board.boardsite.dto.tour.TourOnlyListDto;
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
        String filePath,
        boolean deleted

) {

    public static TourResponse of(Long id,
                                  LocalDateTime createdAt,
                                  String title,
                                  String content,
                                  int readCount,
                                  Long thumbnailId,
                                  String city,
                                  String filePath,
                                  boolean deleted) {
        return new TourResponse(id,
                createdAt,
                title,
                content,
                readCount,
                thumbnailId,
                city,
                filePath,
                deleted);
    }

    /*
      리스트 불러오기 위해 새로 생성
     */
    public static TourResponse from(TourOnlyListDto dto){
        return new TourResponse(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getTitle(),
                dto.getContent(),
                dto.getReadCount(),
                dto.getThumbnailId(),
                dto.getCity(),
                dto.getFilePath(),
                dto.isDeleted()
        );
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
                dto.filePath(),
                dto.deleted()
        );
    }
}
