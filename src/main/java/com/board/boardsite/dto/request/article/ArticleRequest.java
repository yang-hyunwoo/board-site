package com.board.boardsite.dto.request.article;

import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record ArticleRequest(

        @ApiModelProperty(value ="title" , example = "제목" , required = true)
        String title,

        @ApiModelProperty(value ="content" , example = "내용" , required = true)
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
