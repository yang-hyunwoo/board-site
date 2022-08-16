package com.board.boardsite.dto.article;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.dto.user.TripUserDto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsDto(
        Long id,
        TripUserDto tripUserDto,
        Set<ArticleCommentDto> articleCommentDtos,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleWithCommentsDto of(Long id,
                                  TripUserDto tripUserDto,
                                  Set<ArticleCommentDto> articleCommentDtos,
                                  String title,
                                  String content,
                                  LocalDateTime createdAt,
                                  String createdBy,
                                  LocalDateTime modifiedAt,
                                  String modifiedBy) {
        return new ArticleWithCommentsDto(
                id,
                tripUserDto,
                articleCommentDtos,
                title,
                content,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article entity){
        return new ArticleWithCommentsDto(
                entity.getId(),
                TripUserDto.from(entity.getTripUser()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
