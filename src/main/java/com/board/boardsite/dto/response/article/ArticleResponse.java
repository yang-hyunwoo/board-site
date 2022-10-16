package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content ,
        String email,
        int readCount,
        boolean deleted,
        String nickName
) {

    public static ArticleResponse of(Long id,
                                     LocalDateTime createdAt,
                                     String title,
                                     String content,
                                     String email,
                                     int readCount,
                                     boolean deleted,
                                     String nickName) {
        return new ArticleResponse(id,
                createdAt,
                title,
                content,
                email,
                readCount,
                deleted,
                nickName);
    }

    public static ArticleResponse from(ArticleDto dto){
        return new ArticleResponse(
                dto.id(),
                dto.createdAt(),
                dto.title(),
                dto.content(),
                dto.tripUser().email(),
                dto.readCount(),
                dto.deleted(),
                dto.tripUser().nickName()
        );
    }
}
