package com.board.boardsite.config;

import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.user.TripUserService;
import com.board.boardsite.support.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String key;

    private final TripUserService tripUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")) {
            log.error("Error occurs while getting header. header is null or invalid");
            filterChain.doFilter(request,response);
            return ;
        }

        try {
            final String token = header.split(" ")[1].trim();

            //TODO : check token is valid
            if(JwtTokenUtils.isExpired(token,key)){
                log.error("Key is expired");
                filterChain.doFilter(request,response);
                return ;
            }

            //TODO : get username from token
            String email = JwtTokenUtils.getEmail(token , key);
            TripUserPrincipal user = tripUserService.loadUserByEmail(email);

            // TODO: check the user is valid
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user,null, user.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (RuntimeException e){
            log.error("Error occurs while validating {}", e);
            filterChain.doFilter(request , response);
            return ;
        }
        filterChain.doFilter(request,response);

    }
}
