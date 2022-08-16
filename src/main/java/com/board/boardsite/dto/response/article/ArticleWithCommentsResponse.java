package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.security.TripUserPrincipal;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse (
        Long id,
        String content,
        String email,
        String nickName,
        Set<ArticleCommentResponse> articleCommentResponses,
        boolean authChk
){
    public static ArticleWithCommentsResponse of(Long id,
                                       String content,
                                       String email,
                                       String nickName,
                                       Set<ArticleCommentResponse> articleCommentResponses,
                                        boolean authChk) {
        return new ArticleWithCommentsResponse(
                id,
                content,
                email,
                nickName,
                articleCommentResponses,
                authChk
        );
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto , TripUserPrincipal tripUserPrincipal) {
        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.content(),
                dto.tripUserDto().email(),
                dto.tripUserDto().nickName(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                tripUserPrincipal.id()== dto.tripUserDto().id() ? true : false
        );
    }
}
