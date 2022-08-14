package com.board.boardsite.dto.request.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;

public record TripUserJoinRequest(
        String name,
        String nickName,
        String email,
        String password,
        Gender gender
)  {
    public static TripUserJoinRequest of(String name,
                                         String nickName,
                                         String email,
                                         String password,
                                         Gender gender) {
        return new TripUserJoinRequest(
            name,nickName,email,password,gender
        );
    }

    public TripUserDto toDto(){
        return TripUserDto.of(email,
                name,
                nickName,
                password,
                gender
        );
    }
}
