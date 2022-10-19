package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.security.TripUserPrincipal;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse (
        Long id,
        String title,
        String content,
        String email,
        String nickName,
        LocalDateTime createdAt,
        int readCount,
        Set<ArticleCommentResponse> articleCommentResponses,
        boolean authChk
){
    public static ArticleWithCommentsResponse of(Long id,
                                       String title,
                                       String content,
                                       String email,
                                       String nickName,
                                       LocalDateTime createdAt,
                                       int readCount,
                                       Set<ArticleCommentResponse> articleCommentResponses,
                                        boolean authChk) {
        return new ArticleWithCommentsResponse(
                id,
                title,
                content,
                email,
                nickName,
                createdAt,
                readCount,
                articleCommentResponses,
                authChk
        );
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto , TripUserPrincipal tripUserPrincipal) {
        if(tripUserPrincipal == null) {
            return new ArticleWithCommentsResponse(
                    dto.id(),
                    dto.title(),
                    dto.content(),
                    dto.tripUserDto().email(),
                    dto.tripUserDto().nickName(),
                    dto.createdAt(),
                    dto.readCount(),
                    dto.articleCommentDtos().stream()
                            .map(ArticleCommentResponse::from)
                            .collect(Collectors.toCollection(LinkedHashSet::new)),
                    false
            );
        } else {
            return new ArticleWithCommentsResponse(
                    dto.id(),
                    dto.title(),
                    dto.content(),
                    dto.tripUserDto().email(),
                    dto.tripUserDto().nickName(),
                    dto.createdAt(),
                    dto.readCount(),
                    dto.articleCommentDtos().stream()
                            .map(ArticleCommentResponse::from)
                            .collect(Collectors.toCollection(LinkedHashSet::new)),
                    tripUserPrincipal.id() == dto.tripUserDto().id() ? true : false
            );

        }
    }
}
