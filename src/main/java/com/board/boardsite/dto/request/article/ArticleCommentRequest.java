package com.board.boardsite.dto.request.article;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.user.TripUserDto;

public record ArticleCommentRequest(
        Long articleId,
        String content
)
{
    public static ArticleCommentRequest of(Long articleId,String content) {
        return new ArticleCommentRequest(articleId,content);
    }

    public ArticleCommentDto toDto(TripUserDto tripUserDto){
        return ArticleCommentDto.of(
                articleId,
                tripUserDto,
                content
        );
    }
}
