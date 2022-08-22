package com.board.boardsite.dto.article;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        TripUserDto tripUser,
        String title,
        String content,
        boolean deleted,

        int readCount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)
{
    public static ArticleDto of(
                      TripUserDto tripUser,
                      String title,
                      String content) {
        return new ArticleDto(null,
                tripUser,
                title,
                content,
                false,
                0,
                null,
                null,
                null,
                null);
    }

    public static ArticleDto of(Long id,
                      TripUserDto tripUser,
                      String title,
                      String content,
                      boolean deleted,
                      int readCount,
                      LocalDateTime createdAt,
                      String createdBy,
                      LocalDateTime modifiedAt,
                      String modifiedBy) {
     return new ArticleDto(id,
             tripUser,
             title,
             content,
             deleted,
             readCount,
             createdAt,
             createdBy,
             modifiedAt,
             modifiedBy);
    }

    public static ArticleDto from(Article entity){
        return new ArticleDto(
                entity.getId(),
                TripUserDto.from(entity.getTripUser()),
                entity.getTitle(),
                entity.getContent(),
                entity.isDeleted(),
                entity.getReadCount(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity(TripUser tripUser) {
        return Article.of(
                title,
                content,
                deleted,
                readCount,
                tripUser
        );
    }
}
