package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse (
        Long id,
        String content,
        String email,
        String nickName,
        Set<ArticleCommentResponse> articleCommentResponses
){
    public static ArticleWithCommentsResponse of(Long id,
                                       String content,
                                       String email,
                                       String nickName,
                                       Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(
                id,
                content,
                email,
                nickName,
                articleCommentResponses
        );
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.content(),
                dto.tripUserDto().email(),
                dto.tripUserDto().nickName(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}
