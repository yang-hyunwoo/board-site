package com.board.boardsite.dto.user;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.user.TripUser;

import java.time.LocalDateTime;

public record AdmTripUserDto(
        Long id,
        String email,
        String name,
        String nickName,
        String phoneNumber,
        int point,
        boolean deleted,
        Gender gender,
        Boolean emailAuth,
        String role,
        Long travelAgencyId,
        boolean authChk,
        Long profileId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AdmTripUserDto of(
                                 String email,
                                 String name,
                                 String nickName,
                                 String phoneNumber,
                                 String password,
                                 Gender gender,
                                 String role,
                                 Long travelAgencyId) {
        return new AdmTripUserDto(null,
                email,
                name,
                nickName,
                phoneNumber,
                0,
                false,
                gender,
                false,
                role,
                travelAgencyId,
                false,
                null,
                null,
                null,
                null,
                null);
    }

    public static AdmTripUserDto of(
            String nickName,
            String phoneNumber,
            Gender gender,
            Long profileId,
            String role,
            Long travelAgencyId) {
        return new AdmTripUserDto(null,
                null,
                null,
                nickName,
                phoneNumber,
                0,
                false,
                gender,
                false,
                role,
                travelAgencyId,
                false,
                profileId,
                null,
                null,
                null,
                null);
    }


    public static AdmTripUserDto of(Long id,
                                    String email,
                                    String name,
                                    String nickName,
                                    String phoneNumber,
                                    int point,
                                    boolean deleted,
                                    Gender gender,
                                    String role,
                                    Long travelAgencyId,
                                    boolean authChk,
                                    Boolean emailAuth,
                                    Long profileId,
                                    LocalDateTime createdAt,
                                    String createdBy,
                                    LocalDateTime modifiedAt,
                                    String modifiedBy) {
       return new AdmTripUserDto(
               id,
               email,
               name,
               nickName,
               phoneNumber,
               point,
               deleted,
               gender,
               emailAuth,
               role,
               travelAgencyId,
               authChk,
               profileId,
               createdAt,
               createdBy,
               modifiedAt,
               modifiedBy);
    }

    public static AdmTripUserDto from(TripUser entity) {
        return new AdmTripUserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getNickName(),
                entity.getPhoneNumber(),
                entity.getPoint(),
                entity.isDeleted(),
                entity.getGender(),
                entity.getEmailAuth(),
                entity.getRole(),
                entity.getTravelAgencyId(),
                entity.isAuthChk(),
                entity.getProfileId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }




}
