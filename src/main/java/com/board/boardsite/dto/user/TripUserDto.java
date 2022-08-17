package com.board.boardsite.dto.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;

import java.time.LocalDateTime;
public record TripUserDto(
        Long id,
        String email,
        String name,
        String nickName,
        int point,
        boolean deleted,
        String password,
        Gender gender,
        Boolean emailAuth,
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
        return new TripUserDto(null,email,name,nickName,0,false,password,gender,false,null,null,null,null);
    }

    public static TripUserDto of(Long id,
                       String email,
                       String name,
                       String nickName,
                       int point,
                       boolean deleted,
                       String password,
                       Gender gender,
                       Boolean emailAuth,
                       LocalDateTime createdAt,
                       String createdBy,
                       LocalDateTime modifiedAt,
                       String modifiedBy) {
       return new TripUserDto(
               id,
               email,
               name,
               nickName,
               point,
               deleted,
               password,
               gender,
               emailAuth,
               createdAt,
               createdBy,
               modifiedAt,
               modifiedBy);
    }

    public static TripUserDto from(TripUser entity) {
        return new TripUserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getNickName(),
                entity.getPoint(),
                entity.isDeleted(),
                entity.getPassword(),
                entity.getGender(),
                entity.getEmailAuth(),
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
                deleted,
                emailAuth
        );
    }

}
