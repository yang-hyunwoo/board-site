package com.board.boardsite.dto.request.article;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record ArticleCommentRequest(

        @ApiModelProperty(value ="articleId" , example = "1" , required = true)
        Long articleId,

        @ApiModelProperty(value ="content" , example = "내용" , required = true)
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
