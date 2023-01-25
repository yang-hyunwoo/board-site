package com.board.boardsite.dto.request.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record TripUserJoinRequest(
        @ApiModelProperty(value ="name" , example = "이름" , required = true)
        String name,

        @ApiModelProperty(value ="nickName" , example = "닉네임" , required = true)
        String nickName,

        @ApiModelProperty(value ="email" , example = "이메일" , required = true)
        String email,

        @ApiModelProperty(value ="password" , example = "패스워드" , required = true)
        String password,

        @ApiModelProperty(value ="phoneNumber" , example = "휴대폰 번호" , required = true)
        String phoneNumber,

        @ApiModelProperty(value ="성별" , example = "M" , required = true)
        Gender gender,

        @ApiModelProperty(value ="유저ID" , example = "1" , required = true)
        Long travelAgencyId,

        @ApiModelProperty(value ="role" , example = "권한" , required = true)
        String role,

        @ApiModelProperty(value ="로그인 타입" , example = "NAVER" , required = true)
        String loginType
)  {
    public static TripUserJoinRequest of(String name,
                                         String nickName,
                                         String email,
                                         String password,
                                         String phoneNumber,
                                         Gender gender,
                                         Long travelAgencyId,
                                         String role,
                                         String loginType) {
        return new TripUserJoinRequest(
            name,nickName,email,password,phoneNumber,gender,travelAgencyId,role,loginType
        );
    }

    public TripUserDto toDto(){
        return TripUserDto.of(email,
                name,
                nickName,
                phoneNumber,
                password,
                gender,
                role,
                loginType,
                travelAgencyId
        );
    }
}
