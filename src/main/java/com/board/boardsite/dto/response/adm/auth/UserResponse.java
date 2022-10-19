package com.board.boardsite.dto.response.adm.auth;


public record UserResponse(
        Long id ,
        String email,
        String name,
        String nickName,
        String phoneNumber,
        boolean deleted,
        Boolean emailAuth,
        String role,
        Long travelAgencyId,
        boolean authChk,
        Long profileId,
        String travelAgencyName
) {

    public static UserResponse of(Long id,
                        String email,
                        String name,
                        String nickName,
                        String phoneNumber,
                        boolean deleted,
                        Boolean emailAuth,
                        String role,
                        Long travelAgencyId,
                        boolean authChk,
                        Long profileId,
                                  String travelAgencyName) {
        return new UserResponse(id,
                email,
                name,
                nickName,
                phoneNumber,
                deleted,
                emailAuth,
                role,
                travelAgencyId,
                authChk,
                profileId,
                travelAgencyName);
    }

    public static UserResponse from(AdmUserDto dto) {
        return new UserResponse(
                dto.id,
                dto.email,
                dto.name,
                dto.nickName,
                dto.phoneNumber,
                dto.deleted,
                dto.emailAuth,
                dto.role,
                dto.travelAgencyId,
                dto.authChk,
                dto.profileId,
                dto.travelAgencyName
        );
    }



}
