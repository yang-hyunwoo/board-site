package com.board.boardsite.dto.article;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.user.TripUserDto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        TripUserDto tripUser,
        String content,
        boolean deleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(Long id,
                             Long articleId,
                             TripUserDto tripUser,
                             String content,
                             boolean deleted,
                             LocalDateTime createdAt,
                             String createdBy,
                             LocalDateTime modifiedAt,
                             String modifiedBy) {
        return new ArticleCommentDto(
                id,
                articleId,
                tripUser,
                content,
                deleted,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static ArticleCommentDto of(Long articleId,
                                       TripUserDto tripUser,
                                       String content
                                     ) {
        return new ArticleCommentDto(
                null,
                articleId,
                tripUser,
                content,
                false,
                null,
                null,
                null,
                null);
    }

    public static ArticleCommentDto from(ArticleComment entity){
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                TripUserDto.from(entity.getTripUser()),
                entity.getContent(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article article , TripUser tripUser) {
        return ArticleComment.of(
                article,
                content,
                deleted,
                tripUser
        );

    }
}
