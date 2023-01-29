package com.board.boardsite.dto.request.user;

import io.swagger.annotations.ApiModelProperty;

public record TripUserPasswordFindRequest(
        @ApiModelProperty(value ="이름" , example = "양" , required = true)
        String name,
        @ApiModelProperty(value ="이메일" , example = "test@test.com" , required = true)
        String email
)
{
}
