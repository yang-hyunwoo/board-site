package com.board.boardsite.config;

import com.board.boardsite.dto.security.TripUserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
            return () -> {
                Authentication authentication
                        = SecurityContextHolder.getContext().getAuthentication();
                if(authentication==null){
                    return Optional.of("System");
                } else {
                if(authentication.getPrincipal().equals("anonymousUser")){
                    return Optional.of("init");
                }else {
                    return Optional.ofNullable(authentication)
                            .map(Authentication::getPrincipal)
                            .map(TripUserPrincipal.class::cast)
                            .map(TripUserPrincipal::getUsername);
                }
                }
            };
    }
}
