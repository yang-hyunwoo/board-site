package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleCommentDto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickName
) {

    public static ArticleCommentResponse of(Long id,
                                  String content,
                                  LocalDateTime createdAt,
                                  String email,
                                  String nickName) {
        return new ArticleCommentResponse(
                id,
                content,
                createdAt,
                email,
                nickName
                );
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.tripUser().email(),
                dto.tripUser().nickName()
        );
    }
}
