package com.board.boardsite.dto.response.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;

public record TripUserJoinResponse(
        Long id,
        String name,
        String nickName,
        String email,
        int point,
        Gender gender
    )
{
    public static TripUserJoinResponse of(Long id,
                                          String name,
                                          String nickName,
                                          String email,
                                          int point,
                                          Gender gender) {
        return new TripUserJoinResponse(id,
                name,
                nickName,
                email,
                point,
                gender);
    }
    public static TripUserJoinResponse from(TripUserDto dto) {
        return new TripUserJoinResponse(
                dto.id(),
                dto.name(),
                dto.nickName(),
                dto.email(),
                dto.point(),
                dto.gender()
        );
    }

}
