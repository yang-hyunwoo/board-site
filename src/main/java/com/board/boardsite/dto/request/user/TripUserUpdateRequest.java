package com.board.boardsite.dto.request.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;

public record TripUserUpdateRequest(

        @ApiModelProperty(value ="nickName" , example = "닉네임" , required = true)
        String nickName,

        @ApiModelProperty(value ="phoneNumber" , example = "휴대폰 번호" , required = true)
        String phoneNumber,

        @ApiModelProperty(value ="성별" , example = "M" , required = true)
        Gender gender ,

        @ApiModelProperty(value ="프로필 id" , example = "1" , required = false)
        Long profileId,

        @ApiModelProperty(value ="role" , example = "권한" , required = true)
        String role,

        @ApiModelProperty(value ="로그인 타입" , example = "NAVER" , required = true)
        String loginType,

        @ApiModelProperty(value ="여행사Id" , example = "1" , required = true)
        Long travelAgencyId
)  {
    public static TripUserUpdateRequest of(String nickName,
                                           String phoneNumber,
                                           Gender gender,
                                           Long profileId,
                                           String role,
                                           String loginType,
                                           Long travelAgencyId) {
        return new TripUserUpdateRequest(
            nickName,phoneNumber,gender,profileId,role,loginType,travelAgencyId
        );
    }

    public TripUserDto toDto(){
        return TripUserDto.of(
                nickName,
                phoneNumber,
                gender,
                profileId,
                role,
                loginType,
                travelAgencyId
        );
    }
}
