package com.board.boardsite.dto.response.article;

import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.security.TripUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickName,
        boolean authChk
) {

    public static ArticleCommentResponse of(Long id,
                                  String content,
                                  LocalDateTime createdAt,
                                  String email,
                                  String nickName,
                                  boolean authChk) {
        return new ArticleCommentResponse(
                id,
                content,
                createdAt,
                email,
                nickName,
                authChk
                );
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        var articleAuthChk = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(TripUserPrincipal.class::cast);
//        System.out.println("제발:::"+aa.get().email());
        System.out.println("?????");
//        System.out.println(tripUserPrincipal.email());
        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.tripUser().email(),
                dto.tripUser().nickName(),
                dto.tripUser().id() == articleAuthChk.get().id() ? true : false

        );
    }
}
