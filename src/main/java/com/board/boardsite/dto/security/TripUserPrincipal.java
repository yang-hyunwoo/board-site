package com.board.boardsite.dto.security;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.user.TripUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record TripUserPrincipal(
        Long id,
        String name,
        String nickName,
        String email,
        String password,
        String phoneNumber,
        int point,
        boolean deleted,
        Gender gender,
        Long travelAgencyId,
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
