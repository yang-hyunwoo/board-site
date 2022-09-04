package com.board.boardsite.dto.tour;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.tour.Tour;
import com.board.boardsite.domain.tour.TourComment;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;

import java.time.LocalDateTime;

public record TourCommentDto(
        Long id,
        Long tourId,
        TripUserDto tripUser,
        String content,
        boolean deleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static TourCommentDto of(Long id,
                                    Long tourId,
                                    TripUserDto tripUser,
                                    String content,
                                    boolean deleted,
                                    LocalDateTime createdAt,
                                    String createdBy,
                                    LocalDateTime modifiedAt,
                                    String modifiedBy) {
        return new TourCommentDto(
                id,
                tourId,
                tripUser,
                content,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static TourCommentDto of(Long tourId,
                                    TripUserDto tripUser,
                                    String content
                                     ) {
        return new TourCommentDto(
                null,
                tourId,
                tripUser,
                content,
                false,
                null,
                null,
                null,
                null);
    }

    public static TourCommentDto from(TourComment entity){
        return new TourCommentDto(
                entity.getId(),
                entity.getTour().getId(),
                TripUserDto.from(entity.getTripUser()),
                entity.getContent(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public TourComment toEntity(Tour tour , TripUser tripUser) {
        return TourComment.of(
                tour,
                content,
                deleted,
                tripUser
        );

    }
}
