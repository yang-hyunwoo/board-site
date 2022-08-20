package com.board.boardsite.config;

import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.TripUserDto;
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
    @Bean
    public AuditorAware<String> auditorAware() {
        if(SecurityContextHolder.getContext().getAuthentication()==null){
                return () ->Optional.of("userInit");
        }else {
            return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .map(Authentication::getPrincipal)
                    .map(TripUserPrincipal.class::cast)
                    .map(TripUserPrincipal::getUsername);
        }
    }
}
