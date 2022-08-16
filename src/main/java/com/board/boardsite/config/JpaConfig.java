package com.board.boardsite.config;

import com.board.boardsite.dto.security.TripUserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    //TODO : 수정 일어날때 확인 해봐야 함
    @Bean
    public AuditorAware<String> auditorAware() {
//
//        System.out.println("aa::::"+Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .map(Authentication::getPrincipal));
//
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return () -> Optional.of("System");
        }
        TripUserPrincipal tripUser = (TripUserPrincipal) authentication.getPrincipal();
        return () -> Optional.of(tripUser.name());
    }
}
