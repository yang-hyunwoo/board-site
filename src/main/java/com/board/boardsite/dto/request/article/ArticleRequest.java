package com.board.boardsite.dto.request.article;

import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.user.TripUserDto;

public record ArticleRequest(
        String title,
        String content

)
{
    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }
    public ArticleDto toDto(TripUserDto tripUserDto){
        return ArticleDto.of(
                tripUserDto,
                title,
                content
        );
    }
}
