package com.board.boardsite.dto.response.adm.auth;

import com.board.boardsite.domain.user.TripUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class AdmUserDto {
    Long id ;
    String email;
    String name;
    String nickName;
    String phoneNumber;
    boolean deleted;
    Boolean emailAuth;
    String role;
    String loginType;
    Long travelAgencyId;
    boolean authChk;
    Long profileId;
    String travelAgencyName;

    public AdmUserDto(Long id,
                      String email,
                      String name,
                      String nickName,
                      String phoneNumber,
                      boolean deleted,
                      Boolean emailAuth,
                      String role,
                      String loginType,
                      Long travelAgencyId,
                      boolean authChk,
                      Long profileId,
                      String travelAgencyName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.deleted = deleted;
        this.emailAuth = emailAuth;
        this.role = role;
        this.loginType = loginType;
        this.travelAgencyId = travelAgencyId;
        this.authChk = authChk;
        this.profileId = profileId;
        this.travelAgencyName = travelAgencyName;
    }

    public static AdmUserDto from(TripUser entity) {
        return new AdmUserDto(entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getNickName(),
                entity.getPhoneNumber(),
                entity.isDeleted(),
                entity.getEmailAuth(),
                entity.getRole(),
                entity.getLoginType(),
                entity.getTravelAgencyId(),
                entity.isAuthChk(),
                entity.getProfileId(),
                null
                );
    }

}




