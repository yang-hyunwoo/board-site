package com.board.boardsite.dto.security;

import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.dto.user.TripUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record TripUserPrincipal(
        String name,
        String nickName,
        String email,
        String password,
        int point,
        boolean deleted,
        Gender gender,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static TripUserPrincipal of(String name,
                             String nickName,
                             String email,
                             String password,
                             int point,
                             boolean deleted,
                             Gender gender) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new TripUserPrincipal(
                name,
                nickName,
                email,
                password,
                point,
                deleted,
                gender,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static TripUserPrincipal from(TripUserDto dto){
        return TripUserPrincipal.of(
                dto.name(),
                dto.nickName(),
                dto.email(),
                dto.password(),
                dto.point(),
                dto.deleted(),
                dto.gender()
        );
    }

    public TripUserDto toDto() {
        return TripUserDto.of(
                email,
                name,
                nickName,
                password,
                gender
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}