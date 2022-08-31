package com.board.boardsite.dto.request.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;

public record TripUserUpdateRequest(
        String nickName,
        String phoneNumber,
        Gender gender ,
        Long profileId
)  {
    public static TripUserUpdateRequest of(String nickName,
                                           String phoneNumber,
                                           Gender gender,
                                           Long profileId) {
        return new TripUserUpdateRequest(
            nickName,phoneNumber,gender,profileId
        );
    }

    public TripUserDto toDto(){
        return TripUserDto.of(
                nickName,
                phoneNumber,
                gender,
                profileId
        );
    }
}
