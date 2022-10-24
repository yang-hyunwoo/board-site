package com.board.boardsite.config;


import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.CustomAuthenticationEntryPoint;
import com.board.boardsite.repository.user.TripUserRepository;
import com.board.boardsite.service.user.TripUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class AuthenticationConfig {

    @Value("${jwt.secret-key}")
    private String key;

    private final TripUserService tripUserService;



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers( "/api/trip/users/login", "/api/trip/users/join","/api/adm/admin/login","/api/naver/**","/api/kakao/**");

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/adm/excel/**","p/api/chat/detail","/**","/api/common/**","/api/trip/agency-trip/**","/api/trip/agency/**","/api/trip/articles/**","/api/trip/articles","/api/trip/users/confirm-email","/api/adm/admin/list").permitAll()
                .antMatchers("/api/admin/**").hasRole("SUPER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**","/api/tirp/articles/new-article").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(key,tripUserService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and().build();
    }

    @Bean
    public UserDetailsService userDetailsService(TripUserRepository tripUserRepository){
        return email -> tripUserRepository
                .findByEmail(email)
                .map(TripUserDto::from)
                .map(TripUserPrincipal::from)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을수 없습니다. - username: "+ email));
    }

}
