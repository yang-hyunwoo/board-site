package com.board.boardsite.dto.request.user;

import io.swagger.annotations.ApiModelProperty;

public record EmailAuthRequest(

        @ApiModelProperty(value ="email" , example = "이메일" , required = true)
        String email,

        @ApiModelProperty(value ="authToken" , example = "권한 토큰" , required = true)
        String authToken,

        @ApiModelProperty(value ="만료 여부" , example = "false" , required = true)
        Boolean expired
) {
}
