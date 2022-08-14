package com.board.boardsite.dto;

import com.board.boardsite.domain.TripUser;
import com.board.boardsite.domain.constant.Gender;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
public record TripUserDto(
        Long id,
        String email,
        String name,
        String nickName,
        int point,
        String useYn,
        String password,
        Gender gender,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static TripUserDto of(
                                 String email,
                                 String name,
                                 String nickName,
                                 String password,
                                 Gender gender) {
        return new TripUserDto(null,email,name,nickName,0,"Y",password,gender,null,null,null,null);
    }

    public static TripUserDto from(TripUser entity) {
        return new TripUserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getNickName(),
                entity.getPoint(),
                entity.getUseYn(),
                entity.getPassword(),
                entity.getGender(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public TripUser toEntity(String passwordEncode){
        return TripUser.of(
                name,
                nickName,
                email,
                passwordEncode,
                point,
                gender,
                useYn
        );
    }

}
