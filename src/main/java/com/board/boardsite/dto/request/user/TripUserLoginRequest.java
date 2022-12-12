package com.board.boardsite.dto.request.user;

import io.swagger.annotations.ApiModelProperty;

public record TripUserLoginRequest(
        @ApiModelProperty(value ="관리자 이메일" , example = "test@test.com" , required = true)
        String email,
        @ApiModelProperty(value ="관리자 비밀번호" , example = "숫자,영문,특수문자 8자" , required = true)
        String password
)
{
}
