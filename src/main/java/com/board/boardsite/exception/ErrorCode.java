package com.board.boardsite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"이미 사용중이거나 탈퇴한 이메일입니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "Email not founded"),
    EMAIL_TIME_INVAILED(HttpStatus.UNAUTHORIZED, "Email expired"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED , "Password is invalid"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND , "User not founded"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"Token is invalid"),
    ARTICLE_DELETE_FAIL(HttpStatus.NOT_FOUND , "Article is deleted failed"),
    ARTICLE_UPDATE_FAIL(HttpStatus.NOT_FOUND , "Article is failed"),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND , "Article not founded"),

    ARTICLE_COMMENT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED , "Article comment not unauthorized"),

    ARTICLE_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND , "Article comment not founded"),
    TRAVEL_AGENCY_NOT_FOUND(HttpStatus.NOT_FOUND , "TravelAgency not founded"),

    TRAVEL_AGENCY_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "TravelAgencyDetail not founded"),
    ;

    private HttpStatus status;
    private String message;
}
