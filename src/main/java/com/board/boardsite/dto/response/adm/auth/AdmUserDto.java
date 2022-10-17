package com.board.boardsite.dto.response.adm.auth;

import com.board.boardsite.domain.constant.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    Long travelAgencyId;
    boolean authChk;
    Long profileId;
    String travelAgencyName;

}
