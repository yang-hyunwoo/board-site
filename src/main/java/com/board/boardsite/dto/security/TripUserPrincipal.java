package com.board.boardsite.dto.security;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

public record TripUserPrincipal(
        @ApiModelProperty(value ="id" , example = "1" , required = true)
        Long id,

        @ApiModelProperty(value ="이름" , example = "관리자" , required = true)
        String name,

        @ApiModelProperty(value ="닉네임" , example = "관리자" , required = true)
        String nickName,

        @ApiModelProperty(value ="email" , example = "test@test.com" , required = true)
        String email,

        @ApiModelProperty(value ="패스워드" , example = "pw" , required = true)
        String password,

        @ApiModelProperty(value ="휴대폰 번호" , example = "010-1111-2222" , required = true)
        String phoneNumber,

        @ApiModelProperty(value ="포인트" , example = "100" , required = true)
        int point,

        @ApiModelProperty(value ="삭제여부" , example = "fasle" , required = true)
        boolean deleted,

        @ApiModelProperty(value ="성별" , example = "M" , required = true)
        Gender gender,

        @ApiModelProperty(value ="로그인 타입" , example = "NAVER" , required = true)
        String loginType,

        @ApiModelProperty(value ="여행사 ID" , example = "1" , required = true)
        Long travelAgencyId,

        @ApiModelProperty(value ="권한" , example = "ADMIN" , required = true)
        String role
) implements UserDetails {

    public static TripUserPrincipal of(Long id ,
                              String name,
                             String nickName,
                             String email,
                             String password,
                             String phoneNumber,
                             int point,
                             boolean deleted,
                             Gender gender,
                             String loginType,
                             Long travelAgencyId,
                             String role) {
        return new TripUserPrincipal(
                id,
                name,
                nickName,
                email,
                password,
                phoneNumber,
                point,
                deleted,
                gender,
                loginType,
                travelAgencyId,
                role
        );
    }

    public static TripUserPrincipal from(TripUserDto dto){
        return TripUserPrincipal.of(
                dto.id(),
                dto.name(),
                dto.nickName(),
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                dto.point(),
                dto.deleted(),
                dto.gender(),
                dto.loginType(),
                dto.travelAgencyId(),
                dto.role()
        );
    }

    public TripUserDto toDto() {
        return TripUserDto.of(
                id,
                email,
                name,
                nickName,
                phoneNumber,
                point,
                deleted,
                password,
                gender,
                role,
                loginType,
                travelAgencyId,
                true,
                true,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for(String role : role.split(",")){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum RoleType {
        SUPER("ROLE_SUPER"),
        ADMIN("ROLE_ADMIN"),
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}
