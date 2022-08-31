package com.board.boardsite.dto.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;

import java.time.LocalDateTime;
public record TripUserDto(
        Long id,
        String email,
        String name,
        String nickName,
        String phoneNumber,
        int point,
        boolean deleted,
        String password,
        Gender gender,
        Boolean emailAuth,
        Long profileId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static TripUserDto of(
                                 String email,
                                 String name,
                                 String nickName,
                                 String phoneNumber,
                                 String password,
                                 Gender gender) {
        return new TripUserDto(null,
                email,
                name,
                nickName,
                phoneNumber,
                0,
                false,
                password,
                gender,
                false,
                null,
                null,
                null,
                null,
                null);
    }

    public static TripUserDto of(
            String nickName,
            String phoneNumber,
            Gender gender,
            Long profileId) {
        return new TripUserDto(null,
                null,
                null,
                nickName,
                phoneNumber,
                0,
                false,
                null,
                gender,
                false,
                profileId,
                null,
                null,
                null,
                null);
    }


    public static TripUserDto of(Long id,
                       String email,
                       String name,
                       String nickName,
                       String phoneNumber,
                       int point,
                       boolean deleted,
                       String password,
                       Gender gender,
                       Boolean emailAuth,
                       Long profileId,
                       LocalDateTime createdAt,
                       String createdBy,
                       LocalDateTime modifiedAt,
                       String modifiedBy) {
       return new TripUserDto(
               id,
               email,
               name,
               nickName,
               phoneNumber,
               point,
               deleted,
               password,
               gender,
               emailAuth,
               profileId,
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
                entity.getPhoneNumber(),
                entity.getPoint(),
                entity.isDeleted(),
                entity.getPassword(),
                entity.getGender(),
                entity.getEmailAuth(),
                entity.getProfileId(),
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
                phoneNumber,
                point,
                gender,
                deleted,
                profileId,
                emailAuth
        );
    }

}
